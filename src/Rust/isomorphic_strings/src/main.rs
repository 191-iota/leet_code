use std::collections::HashMap;

fn main() {
    println!("Hello, world!");
}

// attempt 1 - does not work, checks occurrences but not isomorphism (orders need to be the same)
pub fn is_isomorphic(s: String, t: String) -> bool {
    let mut s_counts = HashMap::new();
    let mut t_counts = HashMap::new();

    for ch in s.chars() {
        *s_counts.entry(ch).or_insert(0) += 1;
    }

    for ch in t.chars() {
        *t_counts.entry(ch).or_insert(0) += 1;
    }

    let mut sv: Vec<_> = s_counts.values().cloned().collect();
    let mut tv: Vec<_> = t_counts.values().cloned().collect();

    sv.sort_unstable();
    tv.sort_unstable();

    sv == tv
}
