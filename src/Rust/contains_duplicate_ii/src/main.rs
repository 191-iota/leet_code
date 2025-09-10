use std::collections::HashMap;
use std::collections::HashSet;
use std::collections::VecDeque;

fn main() {
    println!("Hello, world!");
}

// Attempt 2 - Kind of not faster
pub fn contains_nearby_duplicate(nums: Vec<i32>, k: i32) -> bool {
    let mut seen_set = HashSet::new();
    let mut l = 0;
    let mut r = 0;

    for (i, v) in nums.iter().enumerate() {
        if r - l >= k {
            seen_set.remove(&nums[l as usize]);
            seen_set.insert(v);
        }

        if seen_set.contains(v) {
            return true;
        }

        r += 1;
    }
    return false;
}

// TODO: implement with hashset
//
//
// Attempt 1 - works
pub fn contains_nearby_duplicate(nums: Vec<i32>, k: i32) -> bool {
    if nums.len() == 1 {
        return false;
    }

    let mut seen: HashMap<i32, i32> = HashMap::new();

    for (i, v) in nums.iter().enumerate() {
        if let Some(&j) = seen.get(v) {
            if (i as i32 - j) <= k {
                return true;
            }
        }

        seen.insert(*v, i as i32);
    }

    false
}
