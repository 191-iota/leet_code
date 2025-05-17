fn main() {
    println!("Hello, world!");
}

// attempt 1 using XOR - works
pub fn single_number(nums: Vec<i32>) -> i32 {
    let mut current_single = nums[0];

    for i in 1..nums.len() {
        current_single ^= nums[i];
    }
    current_single
}
