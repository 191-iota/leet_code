fn main() {
    println!("Hello, world!");
}

pub fn two_sum(numbers: Vec<i32>, target: i32) -> Vec<i32> {
    let mut ptr1 = 0;
    let mut ptr2 = numbers.len() - 1;

    loop {
        if numbers[ptr1] + numbers[ptr2] == target {
            return vec![(ptr1 + 1) as i32, (ptr2 + 1) as i32];
        } else if numbers[ptr1] + numbers[ptr2] < target {
            ptr1 += 1;
        } else {
            ptr2 -= 1;
        }
    }
}
