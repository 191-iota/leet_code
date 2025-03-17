fn main() {
    println!("{}", search_insert(vec![1, 2, 3], 2));
}

// Attempt 1o - binary search
pub fn search_insert1o(Vec<i32>, target: i32) -> i32 {
    match nums.binary_search(&target) {
        Ok(index) => index as i32,
        Err(index) => index as i32,
    }
}
// Attempt 1 - works
pub fn search_insert1(nums: Vec<i32>, target: i32) -> i32  {
    for (index, &item) in nums.iter().enumerate() {
        if item > target {
            return index as i32;
        }
    }
    nums.len() as i32 + 1
}
