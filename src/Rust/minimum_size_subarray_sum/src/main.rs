use std::cmp;
use std::collections::HashMap;
use std::i32;

fn main() {
    println!("Hello, world!");
}

// Attempt 2 - Doesn't work, doesn't take into account different combos
pub fn min_sub_array_len_2(target: i32, nums: Vec<i32>) -> i32 {
    let mut current_window = 0;
    let mut value = 0;
    let mut shortest_window = 0;
    for v in nums.iter() {
        value += v;
        current_window += 1;

        if value >= target && shortest_window > current_window
            || value >= target && shortest_window == 0
        {
            shortest_window = current_window;
            current_window = 0;
            value = 0;
        }
    }
    shortest_window
}

// Attempt 1 - O(n) solution
// I have noticed that the sorted array is non-contiguous which invalidates it
pub fn min_sub_array_len(target: i32, nums: Vec<i32>) -> i32 {
    let mut sorted = nums.clone();
    sorted.sort_unstable();
    let mut counter = 0;
    let mut current = 0;
    for v in sorted.iter().rev() {
        counter += 1;
        current += v;
        if current >= target {
            return counter;
        }
    }
    0
}
