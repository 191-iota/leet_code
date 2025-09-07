use std::collections::HashMap;

fn main() {
    println!("Hello, world!");
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
