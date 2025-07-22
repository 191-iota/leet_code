use std::collections::HashMap;

fn main() {
    let test = vec![2, 7, 11, 15];
    two_sum(test.clone(), 9)
        .into_iter()
        .for_each(|v| println!("{v}"));

    two_sum_2(test.clone(), 9)
        .into_iter()
        .for_each(|v| println!("{v}"));
}

// attempt 2 - pretty unsafe code but we're assuming that there won't be any edgecases
pub fn two_sum_2(nums: Vec<i32>, target: i32) -> Vec<i32> {
    let mut map = HashMap::new();

    // 1. put in vec values into hashmap
    nums.iter().enumerate().for_each(|(u, v)| {
        map.insert(v, u);
    });

    for (i, val) in nums.iter().enumerate() {
        let complement = target - val;
        if let Some(v) = map.get(&complement) {
            if i != *v {
                return vec![i as i32, *v as i32];
            }
        }
    }
    vec![]
}

// attempt 1 - works but pretty inefficient
pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
    let mut ptr = 0;
    let mut ptr2 = 1;
    let mut res = Vec::new();
    while ptr < nums.len() - 1 {
        ptr2 = ptr + 1;
        while ptr2 < nums.len() {
            if nums[ptr as usize] + nums[ptr2 as usize] == target {
                res.push(ptr as i32);
                res.push(ptr2 as i32);
                return res;
            }
            ptr2 += 1;
        }
        ptr += 1;
    }

    res
}
