"""
This problem was asked by Microsoft.

Given a clock time in hh:mm format, determine, to the nearest degree, the angle between the hour and the minute hands.

Bonus: When, during the course of a day, will the angle be zero?
"""
def angle(clock):
    hour, minute = int(clock.split(":")[0]), int(clock.split(":")[1])
    # use six'o clock as base
    if hour >= 12:
        hour -= 12
    # calculate hour hand's angle from six
    hour_angle = None
    if hour >= 6:
        hour_angle = (hour - 6) * 30
    else:
        hour_angle = 180 + hour * 30
    # hour_angle += 30 * (minute / 60) does the hour hand move slowly towards next hour?
    # calculate minute hand's angle from six
    minute_angle = None
    if minute >= 30:
        minute_angle = (minute - 30) * 6
    else:
        minute_angle = 180 + minute * 6
    return int(abs(hour_angle - minute_angle))

def test():
    testcases = [("12:00", 0), ("9:00", 90), ("6:00", 180), ("9:15", 180), ("13:15", 60)]
    for test_input, test_answer in testcases:
        assert angle(test_input) == test_answer

if __name__ == "__main__":
    test()    
