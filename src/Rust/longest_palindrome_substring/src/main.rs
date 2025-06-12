use std::collections::BinaryHeap;

fn main() {
    println!("Hello, world!");
}

pub fn longest_palindrome(s: String) -> String {
    let mut longest: &str = "";
    let split_string: Vec<char> = s.chars().collect();

    for i in 0..split_string.len() {
        for j in (0..(split_string.len() / 2)).rev() {
            let mut ptr = i;

            if split_string[ptr] != split_string[split_string.len() - 1 - j] {
                break;
            } else if j == split_string.len() / 2 {
                longest = &[]
            }
        }
    }
    longest.to_string()
}
