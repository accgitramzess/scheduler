${technicalStuff}

begin transaction
begin try

${transactionOperations}

    commit
end try
begin catch
    rollback
    -- some exception
end catch

go