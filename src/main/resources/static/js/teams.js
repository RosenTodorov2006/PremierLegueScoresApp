document.addEventListener("DOMContentLoaded", function () {
    checkAndSetLanguage();

    loadStandings();
    loadLastMatches();

    setInterval(() => {
        loadStandings();
        loadLastMatches();
    }, 49000);

    function loadStandings() {
        fetch('/api/standings')
            .then(response => response.json())
            .then(data => {
                displayStandings(data);
            })
            .catch(error => {
                console.error('Error fetching standings:', error);
            });
    }

    function loadLastMatches() {
        fetch('/api/last-matches')
            .then(response => response.json())
            .then(data => {
                displayLastMatches(data);
            })
            .catch(error => {
                console.error('Error fetching last matches:', error);
            });
    }

    const teamNamesBG = {
        "AFC Bournemouth": "Борнемут",
        "Arsenal FC": "Арсенал",
        "Aston Villa FC": "Астън Вила",
        "Brentford FC": "Брентфорд",
        "Brighton & Hove Albion FC": "Брайтън & Хоув Албиън",
        "Chelsea FC": "Челси",
        "Crystal Palace FC": "Кристъл Палас",
        "Everton FC": "Евертън",
        "Fulham FC": "Фулъм",
        "Ipswich Town FC": "Ипсуич Таун",
        "Leicester City FC": "Лестър Сити",
        "Liverpool FC": "Ливърпул",
        "Manchester City FC": "Манчестър Сити",
        "Manchester United FC": "Манчестър Юнайтед",
        "Newcastle United FC": "Нюкасъл Юнайтед",
        "Nottingham Forest FC": "Нотингам Форест",
        "Southampton FC": "Саутхамптън",
        "Tottenham Hotspur FC": "Тотнъм Хотспър",
        "West Ham United FC": "Уест Хям Юнайтед",
        "Wolverhampton Wanderers FC": "Уулвърхямптън Уондърърс"
    };

    const teamNamesEN = {
        "AFC Bournemouth": "Bournemouth",
        "Arsenal FC": "Arsenal",
        "Aston Villa FC": "Aston Villa",
        "Brentford FC": "Brentford",
        "Brighton & Hove Albion FC": "Brighton & Hove Albion",
        "Chelsea FC": "Chelsea",
        "Crystal Palace FC": "Crystal Palace",
        "Everton FC": "Everton",
        "Fulham FC": "Fulham",
        "Ipswich Town FC": "Ipswich Town",
        "Leicester City FC": "Leicester City",
        "Liverpool FC": "Liverpool",
        "Manchester City FC": "Manchester City",
        "Manchester United FC": "Manchester United",
        "Newcastle United FC": "Newcastle United",
        "Nottingham Forest FC": "Nottingham Forest",
        "Southampton FC": "Southampton",
        "Tottenham Hotspur FC": "Tottenham Hotspur",
        "West Ham United FC": "West Ham United",
        "Wolverhampton Wanderers FC": "Wolverhampton Wanderers"
    };

    function getLanguage() {
        const urlParams = new URLSearchParams(window.location.search);
        const langFromURL = urlParams.get('lang');
        const langFromStorage = localStorage.getItem('selectedLanguage');
        return langFromURL || langFromStorage || 'en_US';
    }

    function checkAndSetLanguage() {
        const lang = getLanguage();
        localStorage.setItem('selectedLanguage', lang);
        const url = new URL(window.location);
        if (url.searchParams.get('lang') !== lang) {
            url.searchParams.set('lang', lang);
            window.location = url.toString();
        }
    }

    function displayStandings(standings) {
        const lang = getLanguage();
        const teamNames = lang === 'bg_BG' ? teamNamesBG : teamNamesEN;

        const standingsTableBody = document.querySelector('#rank tbody');
        standingsTableBody.innerHTML = '';
        standings.forEach(team => {
            const row = document.createElement('tr');
            const translatedName = teamNames[team.name] || team.name;
            row.innerHTML = `
                <th scope="row">${team.position}</th>
                <td colspan="2">${translatedName}</td>
                <td>${team.points}</td>
            `;
            standingsTableBody.appendChild(row);
        });
    }

    function displayLastMatches(matches) {
        const lang = getLanguage();
        const teamNames = lang === 'bg_BG' ? teamNamesBG : teamNamesEN;
        const noDataText = lang === 'bg_BG' ? "-" : "N/A";

        const lastMatchesTableBody = document.querySelector('#lastMatchesTable tbody');
        lastMatchesTableBody.innerHTML = '';

        matches.forEach(match => {
            const row = document.createElement('tr');
            const translatedHomeTeam = teamNames[match.homeTeam] || match.homeTeam;
            const translatedAwayTeam = teamNames[match.awayTeam] || match.awayTeam;
            const homeGoals = match.homeGoals !== "N/A" ? match.homeGoals : noDataText;
            const awayGoals = match.awayGoals !== "N/A" ? match.awayGoals : noDataText;

            const homeTeamCell = document.createElement('td');
            homeTeamCell.textContent = translatedHomeTeam;

            const resultCell = document.createElement('td');
            resultCell.textContent = `${homeGoals} : ${awayGoals}`;

            const awayTeamCell = document.createElement('td');
            awayTeamCell.textContent = translatedAwayTeam;

            row.appendChild(homeTeamCell);
            row.appendChild(resultCell);
            row.appendChild(awayTeamCell);

            lastMatchesTableBody.appendChild(row);
        });
    }
});
