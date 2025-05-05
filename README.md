# ⚽ Champions League Fixture Generator (Java)

A console‑based Java program that simulates fixture generation for the new 36‑team UEFA Champions League “Swiss model.”  
It enforces seeding pots, same‑league restrictions, no duplicate pairings and distributes 144 total matches across 4 matchdays.

---

## 📋 Project Overview

**Main class:** `Champions_League_Fixture.java`

**Key rules implemented:**
1. Each of the 36 teams plays exactly 8 games in the league phase.  
2. There are 4 matchdays; each team plays 2 matches per matchday.  
3. Teams are split into 4 pots of 9 teams each.  
4. Each team must play exactly 2 opponents from each pot.  
5. Clubs from the same domestic league **cannot** face each other (e.g., Barcelona vs Real Madrid is disallowed).  
6. No fixture may be repeated.  
7. Total league‑phase matches = 36 teams × 8 games ÷ 2 = 144 matches.

---

## 🗂 Project Structure

```text
Champions League Fixture/
├── Champions_League_Fixture.java      # Main program for fixture generation
├── Champions_League_Fixture.class     # class file 
└── README.md                          # Readme file
```

---

## 🚀 How to Compile & Run

1. **Clone or download** this repository:
   ```bash
   git clone https://github.com/Amilali09/Champions-League-Fixture-Project.git
   - [View on GitHub](https://github.com/Amilali09/Champions-league-Fixture-Project)
   cd champions-league-fixture
   ```

2. **Compile** the Java source:
   ```bash
   javac Champions_League_Fixture.java
   ```

3. **Run** the program:
   ```bash
   java Champions_League_Fixture
   ```

   You will see console output for each matchday and a sample fixture lookup at the end (e.g., all Real Madrid matches).

> **Requires:** Java 8 or higher.

---

## 🔍 How It Works

1. **Shuffle Pots:** Each pot array (`pot1`–`pot4`) is randomly shuffled.  
2. **AllocateFixtures:** For each matchday, you call `AllocateFixtures()`, passing a rotated ordering of the 4 pots so that every team meets the correct combination of opponents.  
3. **Backtracking:**  
   - The `fixtures()` method uses recursive backtracking to pair each team in a pot with valid opponents from another pot.  
   - It checks `ClubCheck()` to avoid same‑league clashes and the `Matches` set to prevent duplicate fixtures.  
4. **Output:**  
   - Prints “MATCHDAY X” followed by numbered matches.  
   - At the end, you can call `GetFixtures("TeamName", Matches)` to list all fixtures for a given team.

---

## 🛠️ Future Enhancements

- Working on to add a frontend and features like looking for all teams,teams from a specific league.
- Still you have `GetFixtures("TeamName", Matches)` to look for specific matches by selecting one of the teams.
- Points table management feature is almost done and will be added soon.

---

## 🙋 Author

Made by [SYED AMIL ALI](0176CS221205)
Feel free to reach out at aamilakil@gmail.com
