package com.genericscheduler.mapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PlainObjectMapper {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, String> map(List<String> pathsToFind) throws IOException {
        JsonNode root = objectMapper.readTree(new File("C:\\Users\\RKuzmin\\IdeaProjects\\generic-scheduler\\src\\main\\resources\\json_example.json"));
        Map<String, String> map = new HashMap<>();
        for (String path : pathsToFind) {
             JsonNode result = root.findPath(path);
            if (Objects.nonNull(result)) {
                map.put(path, result.asText());
            }
        }

        return map;
    }

    public Map<String, String> map2(List<String> pathsToFind) throws IOException {
        JsonNode root = objectMapper.readTree(new File("C:\\Users\\RKuzmin\\IdeaProjects\\generic-scheduler\\src\\main\\resources\\json_example.json"));
        Map<String, String> plainObject = iterateOverFields(pathsToFind, root);

        return plainObject;
    }


    private Map<String, String> iterateOverFields(List<String> pathsToFind, JsonNode root) {
        Stack<StackNode> stack = new Stack<>();
        stack.push(new StackNode(false, root));

        Map<String, String> plainObject = new HashMap<>();

        while(!stack.isEmpty()) {

            StackNode popStackNode = stack.pop();

            popStackNode.jsonNode.fieldNames().forEachRemaining(fieldName -> {

                JsonNode childNode = popStackNode.jsonNode.get(fieldName);
                // object
                if (childNode.isObject()) {
                    System.out.println("Push -> " + fieldName);
                    StackNode childStackNode = new StackNode(true, childNode);
                    stack.push(childStackNode);
                }
                else if (childNode.isArray()) {
                    if (childNode.fieldNames().hasNext()) {
                        StackNode childStackNode = new StackNode(true, childNode);
                        stack.push(childStackNode);
                    } else {
                        System.out.println("On primitive - " + fieldName);
                        Optional<String> optionalPath = pathsToFind
                                .stream()
                                .filter(p -> p.equals(fieldName))
                                .findFirst();
                        if (optionalPath.isPresent()) {
                            System.out.println("Found -> " + fieldName);
                            plainObject.put(fieldName, childNode.textValue());
                        } else {
                            System.out.println("Not Found -> " + fieldName);
                        }
                    }
                }
                // primitive
                else {
                    System.out.println("On primitive - " + fieldName);
                    Optional<String> optionalPath = pathsToFind
                            .stream()
                            .filter(p -> p.equals(fieldName))
                            .findFirst();
                    if (optionalPath.isPresent()) {
                        System.out.println("Found -> " + fieldName);
                        plainObject.put(fieldName, childNode.textValue());
                    } else {
                        System.out.println("Not Found -> " + fieldName);
                    }
                }
            });
        }

        return plainObject;
    }

    private static class StackNode {

        public boolean isVisited;

        public JsonNode jsonNode;

        public StackNode(boolean isVisited, JsonNode jsonNode) {
            this.isVisited = isVisited;
            this.jsonNode = jsonNode;
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> paths = new ArrayList<>();
        /*paths.add("glossary.GlossDiv.GlossList.GlossEntry.ID");
        paths.add("glossary.GlossDiv.GlossList.GlossEntry.SortAs");
        paths.add("glossary.GlossDiv.GlossList.GlossEntry.GlossTerm");
        paths.add("glossary.GlossDiv.GlossList.GlossEntry.GlossSee");*/
        paths.add("ID");
        paths.add("SortAs");
        paths.add("GlossTerm");
        paths.add("GlossSee");
        paths.add("GlossSeeAlso");
        paths.add("para");

        PlainObjectMapper plainObjectMapper = new PlainObjectMapper();
        Map<String, String> mapResult = plainObjectMapper.map2(paths);

        System.out.println(mapResult);
    }
}
