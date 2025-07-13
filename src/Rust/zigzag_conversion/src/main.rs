fn main() {
    println!("Hello, world!");
}

pub fn convert(s: String, num_rows: i32) -> String {
    if num_rows == 1 || num_rows as usize >= s.len() {
        return s;
    }

    let mut rows = vec![String::new(); num_rows as usize];
    let mut row = 0;
    let mut down = true;

    let mut chars = s.chars();

    while let Some(c) = chars.next() {
        rows[row].push(c);

        if down {
            if row + 1 < num_rows as usize {
                row += 1;
            } else {
                down = false;
                row -= 1;
            }
        } else if row > 0 {
            row -= 1;
        } else {
            down = true;
            row += 1;
        }
    }

    rows.concat()
}
