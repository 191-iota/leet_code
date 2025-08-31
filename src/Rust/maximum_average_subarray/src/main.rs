use std::cmp;
use std::thread::current;

fn main() {
    println!("Hello, world!");
}

// Attempt 1
pub fn find_max_average(nums: Vec<i32>, k: i32) -> f64 {
    let mut l_ptr = 0;
    let mut r_ptr = k;

    let mut max_avg: f64 = 0.0;

    let mut current_window = &nums[0..k as usize];
    let mut current_sum: i32 = current_window.iter().sum();
    while r_ptr < (nums.len() - 1) as i32 {
        if (current_sum as f64 / k as f64) > max_avg {
            max_avg = (current_sum as f64 / k as f64);
        }

        if r_ptr < nums.len() as i32 {
            current_sum -= nums[l_ptr];
            l_ptr += 1;
            r_ptr += 1;
            current_sum += nums[r_ptr as usize];
        }
    }

    max_avg
}
