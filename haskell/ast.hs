data Expr = Plus Expr Expr | Minus Expr Expr | Times Expr Expr | Div Expr Expr
    | Literal Float

eval :: Expr -> Float

eval (Div a b) =
    let x = (eval a) / (eval b) in
        x

eval (Times a b) =
    let x = (eval a) * (eval b) in
        x 

eval (Minus a b) =
    let x = (eval a) - (eval b) in
        x

eval (Plus a b)  = 
    let x = (eval a) + (eval b) in
        x

eval (Literal x) = x

-- eval Plus Expr1 Expr2 = eval Expr2 + eval Expr2

-- Should output "5.0"
test1 = 
    let input = Plus (Literal 3.0) (Literal 2.0) in
    eval input

-- Should output "3.5"
test2 = 
    let input = Plus (Literal 3.0) (Div (Literal 1.0) (Literal 2.0)) in
    eval input

-- Should output "15.5"
test3 = 
    let input = Plus (Times (Literal 3.0) (Literal 5.0)) (Div (Literal 1.0) (Literal 2.0)) in
    eval input

equals :: Expr -> Expr -> Bool
equals e1 e2 = 
    let x = eval e1 in 
        let y = eval e2 in
            x == y

-- Should output "True"
test4 =
    let input1 = Plus (Literal 5.0) (Div (Literal 1.0) (Literal 2.0)) in
    equals input1 input1

-- Should output "True"
test5 =
    let input1 = Plus (Literal 5.0) (Div (Literal 1.0) (Literal 2.0)) in
    let input2 = Plus (Literal 5.0) (Div (Literal 1.0) (Literal 2.0)) in
    equals input1 input2

-- Should output "False"
test6 =
    let input1 = Plus (Literal 5.0) (Div (Literal 1.0) (Literal 2.0)) in
    let input2 = Plus (Literal 5.0) (Literal 2.0) in
    equals input1 input2

