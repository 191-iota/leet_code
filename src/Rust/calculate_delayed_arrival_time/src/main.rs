fn main() {
    println!("Hello, world!");
}

fn find_delayed_arrival_time_2(arrival_time: i32, delayed_time: i32) -> i32 {
    (arrival_time + delayed_time) % 24
}

fn find_delayed_arrival_time(arrival_time: i32, delayed_time: i32) -> i32 {
    if arrival_time + delayed_time > 23 {
        arrival_time + delayed_time - 24
    } else {
        arrival_time + delayed_time
    }
}
