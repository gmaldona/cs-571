-- reference:
--    ws3-solutions/counter.hs

import Control.Applicative (Applicative(..))
import Control.Monad       (liftM, ap)

newtype BankOp a = BankOp (Double -> (a, Double))

instance Monad BankOp where

    (>>=) :: BankOp a -> (a -> BankOp b) -> BankOp b
    (BankOp cop1) >>= f =
        BankOp (\c ->
            let (a, c') = cop1 c in
            let BankOp cop2 = f a in
            let (b, c'') = cop2 c' in
            (b, c'')
        )

instance Functor BankOp where
    fmap = liftM

instance Applicative BankOp where
    pure x  = BankOp (\c -> (x, c))
    (<*>) = ap

deposit :: Double -> BankOp Double
deposit x = BankOp (\b -> (b + x, b + x))

withdraw :: Double -> BankOp Double
withdraw x = BankOp(\b -> (b - x, b - x))

getBalance :: BankOp Double
getBalance =  BankOp(\b -> (b, b))

getOverdrawn :: BankOp Bool
getOverdrawn = BankOp(\b -> (b < 0, b))

runBankOp :: BankOp a -> a
runBankOp (BankOp co) = let (x, _) = co 0 in x


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


-- -- Should output "(200,-100)"
test2 = 
    (let bankOp = do
            deposit 100.0
            c <- withdraw 400.0
            b <- getBalance
            return (c, b)
    in
    runBankOp bankOp)