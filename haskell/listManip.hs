import Prelude

prefix :: Eq a => [a] -> [a] -> Bool

prefix [] list2 = True

prefix list1 list2 = 
    let h1 = head list1 in
    let h2 = head list2 in
            if h1 == h2 then 
                prefix (tail list1) (tail list2)
            else
                False

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


sublist :: [Int] -> [[Int]]
sublist [] = [[]]
sublist (x:t) =
    sublist t ++ [x:t' | t' <- sublist t]


-- Should output "[[], [1], [2], [3], [1, 2], [2, 3], [1,3], [1, 2, 3]]"
test3 =
    let input = [1,2,3] in
    sublist input