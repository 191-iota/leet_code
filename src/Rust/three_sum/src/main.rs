use std::collections::HashSet;

fn main() {
    println!("Hello, world!");
}

pub fn three_sum(nums: Vec<i32>) -> Vec<Vec<i32>> {
    // fixed pointer
    let mut sorted = nums.clone();
    sorted.sort();

    let mut res: Vec<Vec<i32>> = Vec::new();
    let mut set: HashSet<Vec<i32>> = HashSet::new();
    let mut ptr1 = 0;
    let mut ptr2 = sorted.len() - 1;

    // We set a baseline number which is the for loop enummeration and add two sum on top of that number
    for (i, v) in sorted.iter().enumerate() {
        let baseline = v;
        if i > 0 && sorted[i] == sorted[i - 1] {
            continue;
        }
        ptr1 = i + 1;
        ptr2 = sorted.len() - 1;
        while ptr1 < ptr2 {
            if baseline + sorted[ptr1] + sorted[ptr2] == 0 {
                let curr = vec![*baseline, sorted[ptr1], sorted[ptr2]];
                if !set.contains(&curr) {
                    res.push(vec![*baseline, sorted[ptr1], sorted[ptr2]]);
                    set.insert(curr);
                }
                ptr1 += 1;
                ptr2 -= 1;
            } else if baseline + sorted[ptr1] + sorted[ptr2] < 0 {
                if ptr1 >= sorted.len() - 1 {
                    break;
                }
                ptr1 += 1;
            } else {
                if ptr2 <= 0 {
                    break;
                }
                ptr2 -= 1;
            }
        }
    }
    res
}
