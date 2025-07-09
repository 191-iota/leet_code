fn main() {
    println!("Hello, world!");
}

pub fn convert(s: String, num_rows: i32) -> String {
    // loop through the string
    // write vertically num rows
    // when reached continue by \n and going up one line till
    let mut char = s.chars();

    let mut out = String::new();

    let mut counter = 0;
    let mut row_loop = true;

    while let Some(v) = char.next() {
        while counter % num_rows != 0 && row_loop {
            out.push(v);
            out.push('\n');
            char.next();
            counter += 1;
        }

        row_loop = true;
    }
    out
}
