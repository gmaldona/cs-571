
prefix :: Eq a => [a] -> [a] -> Bool
prefix = ???

-- Should output "True"
test1 = 
    let input1 = [1,2,3] in
    let input2 = [1,2,3,4] in
    prefix input1 input2

-- Should output "False"
test2 = 
    let input1 = [1,2,3] in
    let input2 = [4,5,6] in
    prefix input1 input2



sublist :: [a] -> [[a]]
sublist lst = ???


-- Should output "[[], [1], [2], [3], [1, 2], [2, 3], [1,3], [1, 2, 3]]"
test3 =
    let input = [1,2,3] in
    sublist input



