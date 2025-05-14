fn main() {
    println!("Hello, world!");
}

fn find_first_occurrence(haystack: String, needle: String) -> i32 {
    haystack.find(&needle).map(|i| i as i32).unwrap_or(-1)
}

// wrong approach
fn find_first_occurrence(haystack: String, needle: String) -> i32 {
    for (i, c) in haystack.chars().enumerate() {
        if c == needle {
            return i as i32;
        }
    }
}
