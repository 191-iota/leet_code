use std::collections::HashMap;

fn main() {
    println!("Hello, world!");
}

// s and t of length m and n
//
// Attempt 1
pub fn min_window(s: String, t: String) -> String {
    if s.len() < t.len() {
        return "".to_string();
    }
    let mut ideal = HashMap::with_capacity(t.len());
    let mut curr = HashMap::new();
    let mut ranges: (usize, usize) = (0, s.len() - 1);
    let chars: Vec<char> = s.chars().collect();
    for s in t.chars() {
        *ideal.entry(s).or_insert(0) += 1;
    }

    let mut matching_keys = 0;
    let mut start_bound: usize = 0;
    for (i, s) in chars.iter().enumerate() {
        if ideal.contains_key(s) {
            *curr.entry(s).or_insert(0) += 1;
            if ideal.get(s).unwrap() == curr.get(s).unwrap() {
                matching_keys += 1;
            }

            if matching_keys == ideal.len() && (ranges.1 - ranges.0) > (i - start_bound) {
                ranges = (start_bound, i);
            }

            // make window smaller left to right
            loop {
                let current_char = &chars[start_bound];
                if curr.contains_key(current_char) {
                    let count = *curr.get(current_char).unwrap();
                    if let Some(v) = curr.get_mut(&chars[start_bound]) {
                        if *ideal.get(current_char).unwrap() == count {
                            if matching_keys == ideal.len()
                                && (ranges.1 - ranges.0) > (i - start_bound)
                            {
                                ranges = (start_bound, i);
                            }
                            *v -= 1;
                            matching_keys -= 1;
                            start_bound += 1;
                            break;
                        }
                    }
                }
                start_bound += 1;
            }
        }
    }
    s[ranges.0..=ranges.1].to_string()
}
