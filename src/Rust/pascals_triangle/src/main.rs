fn main() {
    println!("Hello, world!");
}

pub fn generate(num_rows: i32) -> Vec<Vec<i32>> {
    let mut res: Vec<Vec<i32>> = Vec::new();
    res.push(vec![1]);

    if num_rows >= 2 {
        res.push(vec![1, 1]);
    } else {
        return res;
    }
    for i in 2..num_rows {
        res.push(add_row(&res));
    }

    res
}

fn add_row(curr: &Vec<Vec<i32>>) -> Vec<i32> {
    let last_row = curr[curr.len() - 1].clone();
    let mut new_row: Vec<i32> = Vec::with_capacity(last_row.len() + 1);
    new_row.resize(last_row.len() + 1, 0);

    new_row[0] = 1;
    let last_idx = new_row.len() - 1;
    new_row[last_idx] = 1;

    for i in 1..(new_row.len() - 2) {
        new_row[i] = last_row[i - 1] + last_row[i];
    }

    new_row
}
