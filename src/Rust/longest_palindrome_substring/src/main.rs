fn main() {
    println!("Hello, world!");
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

    for i in (0..(split_string.len() / 2)).rev() {
        if i > 0 && split_string[ptr] != split_string[i] {
            longest_range = (i as i32 + 1, ptr as i32 - 1);
            ptr += 1;
        }
    }
    split_string[longest_range.0 as usize..longest_range.1 as usize]
        .iter()
        .collect()
}
