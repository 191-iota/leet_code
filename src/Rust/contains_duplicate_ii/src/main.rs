use std::collections::HashMap;
use std::collections::HashSet;
use std::collections::VecDeque;

fn main() {
    println!("Hello, world!");
}

pub fn contains_nearby_duplicate(nums: Vec<i32>, k: i32) -> bool {
    let mut seen_set = HashSet::new();
    let mut queue = VecDeque::new();

    for (i, v) in nums.iter().enumerate() {
        if seen_set.contains(v) {
            let last_index = queue.pop_front().unwrap();
            if (i as i32 - last_index as i32) <= k {
                return true;
            }
        }

        seen_set.insert(v);
        queue.push_back(i);
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
