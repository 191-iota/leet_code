use std::cmp::max;
use std::path::absolute;

fn main() {
    println!("Hello, world!");
}
pub fn longest_palindrome_2(s: String) -> String {
    if s.len() == 1 {
        return s;
    }

    let mut longest_range = (0, 0);
    let chars: Vec<char> = s.chars().collect();

    for i in 0..s.len() {
        let mut l = i as i32;
        let mut r = i as i32;

        if i + 1 < s.len() && chars[i] == chars[i + 1] {
            r += 1;
        }

        while l >= 0 && (r as usize) < chars.len() && chars[l as usize] == chars[r as usize] {
            l -= 1;
            r += 1;
        }
        // move back to last valid match
        l += 1;
        r -= 1;
        if (r - l) > (longest_range.1 - longest_range.0) {
            longest_range = (l, r);
        }
    }
    s[longest_range.0 as usize..=longest_range.1 as usize].to_string()
}
// attempt 1 - does not work
pub fn longest_palindrome(s: String) -> String {
    if s.len() == 1 {
        return s;
    }

    let mut longest_range: (i32, i32) = (0, 0);
    let split_string: Vec<char> = s.chars().collect();

    let mut ptr;
    if split_string.len() % 2 == 0 {
        ptr = (split_string.len() / 2) + 1;
    } else {
        ptr = split_string.len() / 2;
    }

    let tmp_ptr = ptr;
    for i in (0..(split_string.len() / 2)).rev() {
        for j in (0..i).rev() {
            if ptr >= split_string.len() || j >= split_string.len() {
                break;
            }
            if split_string[ptr] != split_string[j] {
                longest_range = (j as i32, ptr as i32);
            }

            ptr -= 1;
        }
        ptr = tmp_ptr;
        for j in i..split_string.len() {
            if ptr >= split_string.len() || j >= split_string.len() {
                break;
            }
            if split_string[ptr] != split_string[j] {
                longest_range = (j as i32, ptr as i32);
            }

            ptr += 1;
        }
        ptr = tmp_ptr;
    }
    split_string[longest_range.0 as usize..longest_range.1 as usize]
        .iter()
        .collect()
}
