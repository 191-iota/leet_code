use std::collections::HashMap;

fn main() {
    println!("Hello, world!");
}

// s and t of length m and n
//
// Attempt 1
pub fn min_window(s: String, t: String) -> String {
    let mut ideal = HashMap::with_capacity(t.len());
    let mut curr = HashMap::new();
    let mut ranges: Vec<(usize, usize)> = Vec::new();
    let chars: Vec<char> = s.chars().collect();
    for s in t.chars() {
        *ideal.entry(s).or_insert(0) += 1;
    }

    let right = true;
    let matching_keys = 0;
    let start_bound: usize = 0;
    for (i, s) in chars.iter().enumerate() {
        if ideal.contains_key(&s) {
            *curr.entry(s).or_insert(0) += 1;
            if ideal.get(&s).unwrap() == curr.get(&s).unwrap() {
                matching_keys += 1;
            }

            if matching_keys == ideal.len() {
                ranges.push((start_bound, i));
            }

            // make window smaller left to right
            loop {
                let current_char = &chars[start_bound];
                if curr.contains_key(current_char) {
                    if let Some(v) = curr.get_mut(&chars[start_bound]) {
                        *v -= 1;
                    }
                    break;
                }
            }
        }
    }
}
