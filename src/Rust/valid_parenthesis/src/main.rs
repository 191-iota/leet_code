use std::usize;

fn main() {
    println!("Hello, world!");
}

fn is_valid(s: String) -> bool {
    let chars: Vec<char> = s.chars().collect();
    let mut window = vec![0; chars.len()];
    if chars.len() % 2 != 0 {
        return false;
    }

    for i in 0..chars.len() {
        match chars[i] {
            ')' => window[i] = 1,
            '}' => window[i] = 2,
            ']' => window[i] = 3,
            '[' => window[i] = -3,
            '{' => window[i] = -2,
            '(' => window[i] = -1,
            _ => unreachable!(),
        }
    }

    let mut top: isize = -1;
    for i in 0..window.len() {
        if window[i] < 0 {
            top += 1;
            window[top as usize] = window[i];
        } else {
            if top as i32 == -1 || window[top as usize] + window[i] != 0 {
                return false;
            }
            top -= 1;
        }
    }
    top == -1
}
