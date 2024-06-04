-- Вмъкни цветовете
INSERT INTO colors (name) VALUES
                              ('Red'),
                              ('Blue'),
                              ('Green'),
                              ('Yellow'),
                              ('Black'),
                              ('White');

-- Вмъкни страните
INSERT INTO countries (name) VALUES
    ('England');

-- Вмъкни градовете
INSERT INTO towns (name, country_id) VALUES
                                         ('London', (SELECT id FROM countries WHERE name = 'England')),
                                         ('Leicester', (SELECT id FROM countries WHERE name = 'England')),
                                         ('Nottingham', (SELECT id FROM countries WHERE name = 'England')),
                                         ('Southampton', (SELECT id FROM countries WHERE name = 'England')),
                                         ('Liverpool', (SELECT id FROM countries WHERE name = 'England')),
                                         ('Manchester', (SELECT id FROM countries WHERE name = 'England')),
                                         ('Brentford', (SELECT id FROM countries WHERE name = 'England')),
                                         ('Brighton', (SELECT id FROM countries WHERE name = 'England')),
                                         ('Ipswich', (SELECT id FROM countries WHERE name = 'England')),
                                         ('Newcastle', (SELECT id FROM countries WHERE name = 'England')),
                                         ('Wolverhampton', (SELECT id FROM countries WHERE name = 'England'));

-- Вмъкни състезанията
INSERT INTO competitions (name) VALUES
    ('Premier League');

-- Вмъкни отборите
INSERT INTO teams (name, initials, created, budget, history_info, fans_info, trophies_info, kit_color, town_id) VALUES
                                                                                                                    ('Arsenal', 'ARS', '1886-01-01', 100000000, 'History info for Arsenal', 'Fans info for Arsenal', 'Trophies info for Arsenal',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Red'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'London')),

                                                                                                                    ('Chelsea', 'CHE', '1905-01-01', 120000000, 'History info for Chelsea', 'Fans info for Chelsea', 'Trophies info for Chelsea',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Blue'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'London')),

                                                                                                                    ('Leicester City', 'LEI', '1884-01-01', 80000000, 'History info for Leicester City', 'Fans info for Leicester City', 'Trophies info for Leicester City',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Blue'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'Leicester')),

                                                                                                                    ('Nottingham Forest', 'NFO', '1865-01-01', 60000000, 'History info for Nottingham Forest', 'Fans info for Nottingham Forest', 'Trophies info for Nottingham Forest',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Red'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'Nottingham')),

                                                                                                                    ('Southampton', 'SOU', '1885-01-01', 70000000, 'History info for Southampton', 'Fans info for Southampton', 'Trophies info for Southampton',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Red'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'Southampton')),

                                                                                                                    ('Liverpool', 'LIV', '1892-01-01', 150000000, 'History info for Liverpool', 'Fans info for Liverpool', 'Trophies info for Liverpool',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Red'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'Liverpool')),

                                                                                                                    ('Crystal Palace', 'CRY', '1905-01-01', 90000000, 'History info for Crystal Palace', 'Fans info for Crystal Palace', 'Trophies info for Crystal Palace',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Blue'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'London')),

                                                                                                                    ('Aston Villa', 'AST', '1874-01-01', 110000000, 'History info for Aston Villa', 'Fans info for Aston Villa', 'Trophies info for Aston Villa',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Blue'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'Birmingham')),

                                                                                                                    ('Bournemouth', 'BOU', '1899-01-01', 50000000, 'History info for Bournemouth', 'Fans info for Bournemouth', 'Trophies info for Bournemouth',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Red'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'Bournemouth')),

                                                                                                                    ('Everton', 'EVE', '1878-01-01', 130000000, 'History info for Everton', 'Fans info for Everton', 'Trophies info for Everton',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Blue'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'Liverpool')),

                                                                                                                    ('Manchester City', 'MCI', '1880-01-01', 180000000, 'History info for Manchester City', 'Fans info for Manchester City', 'Trophies info for Manchester City',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Blue'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'Manchester')),

                                                                                                                    ('Tottenham Hotspur', 'TOT', '1882-01-01', 140000000, 'History info for Tottenham Hotspur', 'Fans info for Tottenham Hotspur', 'Trophies info for Tottenham Hotspur',
                                                                                                                     (SELECT id FROM colors WHERE name = 'White'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'London')),

                                                                                                                    ('Brentford', 'BRE', '1889-01-01', 60000000, 'History info for Brentford', 'Fans info for Brentford', 'Trophies info for Brentford',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Red'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'Brentford')),

                                                                                                                    ('Fulham', 'FUL', '1879-01-01', 95000000, 'History info for Fulham', 'Fans info for Fulham', 'Trophies info for Fulham',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Black'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'London')),

                                                                                                                    ('Manchester United', 'MUN', '1878-01-01', 200000000, 'History info for Manchester United', 'Fans info for Manchester United', 'Trophies info for Manchester United',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Red'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'Manchester')),

                                                                                                                    ('West Ham United', 'WHU', '1895-01-01', 100000000, 'History info for West Ham United', 'Fans info for West Ham United', 'Trophies info for West Ham United',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Claret'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'London')),

                                                                                                                    ('Brighton & Hove Albion', 'BHA', '1901-01-01', 85000000, 'History info for Brighton & Hove Albion', 'Fans info for Brighton & Hove Albion', 'Trophies info for Brighton & Hove Albion',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Blue'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'Brighton')),

                                                                                                                    ('Wolverhampton Wanderers', 'WOL', '1877-01-01', 90000000, 'History info for Wolverhampton Wanderers', 'Fans info for Wolverhampton Wanderers', 'Trophies info for Wolverhampton Wanderers',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Gold'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'Wolverhampton')),

                                                                                                                    ('Newcastle United', 'NEW', '1892-01-01', 110000000, 'History info for Newcastle United', 'Fans info for Newcastle United', 'Trophies info for Newcastle United',
                                                                                                                     (SELECT id FROM colors WHERE name = 'Black'),
                                                                                                                     (SELECT id FROM towns WHERE name = 'Newcastle'));
