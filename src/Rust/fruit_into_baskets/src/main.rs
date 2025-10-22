use std::cmp;

fn main() {
    println!("Hello, world!");
}

// Does not work
pub fn total_fruit(fruits: Vec<i32>) -> i32 {
    let mut l = 0;
    let mut r = 1;

    if fruits.len() == 1 {
        return 1;
    }

    let mut basket_no_1 = fruits[0];
    let mut basket_no_2 = fruits[1];
    for (i, v) in fruits.iter().enumerate() {
        if *v != basket_no_1 {
            basket_no_2 = *v;
            break;
        }
    }

    let mut max = 2;

    while r < fruits.len() {
        while r < fruits.len() && (fruits[r] == basket_no_2 || fruits[r] == basket_no_1) {
            r += 1;
        }

        max = cmp::max(r as i32 - l as i32, max);

        if r < fruits.len() {
            while fruits[l] == basket_no_1 {
                l += 1;
            }
            basket_no_1 = basket_no_2;
            basket_no_2 = fruits[r];
        }
    }

    max
}
