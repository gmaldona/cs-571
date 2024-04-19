




-- Should output "(True, -50)"
test1 =
    (let bankOp = do
            deposit 1000.0
            withdraw 500.0
            withdraw 550.0
            b <- getBalance
            o <- getOverdrawn
            return (o, b)
    in
    runBankOp bankOp)


-- Should output "(200,-100)"
test2 = 
    (let bankOp = do
            deposit 100.0
            c <- withdraw 400.0
            b <- getBalance
            return (c, b)
    in
    runBankOp bankOp)