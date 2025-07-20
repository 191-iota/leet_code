fn main() {
    println!("Hello, world!");
}

fn maximum_gap(nums: Vec<i32>) -> i32 {
    if nums.len() < 2 {
        return 0;
    }

    let mut sorted = nums.clone();
    sorted.sort();

    let mut max_gap = sorted[1] - sorted[0];
    let mut ptr = 1;

    for x in &sorted[2..] {
        if max_gap < x - sorted[ptr] {
            max_gap = x - sorted[ptr];
        }
        ptr += 1;
    }

    max_gap
}
