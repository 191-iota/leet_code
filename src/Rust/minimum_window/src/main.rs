use std::collections::HashMap;

fn main() {
    println!("Hello, world!");
}

// s and t of length m and n
// TODO: Optimize further
// Attempt X
pub fn min_window(s: String, t: String) -> String {
    if s.len() < t.len() {
        return "".to_string();
    }

    let mut ideal = HashMap::with_capacity(t.len());
    let mut curr = HashMap::new();
    let mut ranges: (usize, usize) = (0, s.len());
    let chars: Vec<char> = s.chars().collect();
    for s in t.chars() {
        *ideal.entry(s).or_insert(0) += 1;
    }

    let mut matching_keys = 0;
    let mut start_bound: usize = 0;
    let mut end_bound: usize = 0;
    let mut found = false;

    while start_bound < chars.len() && end_bound < chars.len() {
        for (i, s) in chars.iter().enumerate().skip(end_bound) {
            if ideal.contains_key(s) {
                *curr.entry(s).or_insert(0) += 1;

                if ideal.get(s).unwrap() == curr.get(s).unwrap() {
                    matching_keys += 1;
                }

                end_bound = i + 1;

                if matching_keys == ideal.len() {
                    found = true;
                    if (i + 1 - start_bound) < (ranges.1 - ranges.0) {
                        ranges = (start_bound, i + 1);
                    }
                    break;
                }
            }
        }
        // make window smaller left to right
        while start_bound < chars.len() {
            let current_char = &chars[start_bound];
            if ideal.contains_key(current_char) && curr.contains_key(current_char) {
                let count = *curr.get(current_char).unwrap();
                if let Some(v) = curr.get_mut(current_char) {
                    if *ideal.get(current_char).unwrap() == count {
                        if matching_keys == ideal.len()
                            && (ranges.1 - ranges.0) > (end_bound - start_bound)
                        {
                            ranges = (start_bound, end_bound);
                            found = true;
                        }
                        *v -= 1;
                        matching_keys -= 1;
                        start_bound += 1;
                        break;
                    } else {
                        *v -= 1;
                    }
                }
            }
            start_bound += 1;
        }
    }

    if found {
        s[ranges.0..ranges.1].to_string()
    } else {
        "".to_string()
    }
}
