document.getElementById('fans-button').addEventListener('click', function() {
    if (document.getElementById('fans-button').textContent === 'Read more') {
        document.getElementById("smallClubInfo").style.display='none';
        // Запазване на оригиналните стойности преди да ги промените
        var cardHistoryDisplay = document.getElementById("card-history").style.display;
        var cardTrophiesDisplay = document.getElementById("card-trophies").style.display;
        var cardFansMinWidth = document.getElementById("card-fans").style.minWidth;
        var cardFansHeight = document.getElementById("card-fans").style.height;
        var photoMaxHeight = document.getElementById('photo').style.maxHeight;
        var cardBodyDisplay = document.getElementsByClassName('card-body')[1].style.display;

        // Промяна на стиловете
        document.querySelector('.info-fans').style.display = 'flex';
        document.getElementById("card-history").style.display = 'none';
        document.getElementById("card-trophies").style.display = 'none';
        document.getElementById("card-fans").style.minWidth = '60%';
        document.getElementById("card-fans").style.height = '90%';
        document.getElementById('photo').style.maxHeight = "50%";
        document.getElementById('fans-button').textContent = 'Read less';
        document.getElementsByClassName('card-body')[1].style.display = 'flex';
    } else {
        // Възстановяване на оригиналните стилове
        document.getElementById("smallClubInfo").style.display='block';
        document.querySelector('.info-fans').style.display = 'none';
        document.getElementById("card-fans").style.minWidth = cardFansMinWidth;
        document.getElementById("card-fans").style.height = cardFansHeight;
        document.getElementById('photo').style.maxHeight = photoMaxHeight;
        document.getElementById('fans-button').textContent = 'Read more';
        document.getElementsByClassName('card-body')[1].style.display = cardBodyDisplay;
        document.getElementById("card-history").style.display = 'flex';
        document.getElementById("card-trophies").style.display = 'flex';
        document.getElementById("card-fans").style.minWidth = '20%';
        document.getElementById("card-fans").style.height = '73%';
    }
});
//------------------------------------------------------------------------------------------------------------------------------------------------------
document.getElementById('trophies-button').addEventListener('click', function() {
    if (document.getElementById('trophies-button').textContent === 'Read more') {
        document.getElementById("smallClubInfo").style.display='none';
        // Запазване на оригиналните стойности преди да ги промените
        var cardHistoryDisplay = document.getElementById("card-fans").style.display;
        var cardTrophiesDisplay = document.getElementById("card-history").style.display;
        var cardFansMinWidth = document.getElementById("card-trophies").style.minWidth;
        var cardFansHeight = document.getElementById("card-trophies").style.height;
        var photoMaxHeight = document.getElementById('photo3').style.maxHeight;
        var cardBodyDisplay = document.getElementsByClassName('card-body')[2].style.display;

        // Промяна на стиловете
        document.querySelector('.info-trophies').style.display = 'flex';
        document.getElementById("card-fans").style.display = 'none';
        document.getElementById("card-history").style.display = 'none';
        document.getElementById("card-trophies").style.minWidth = '60%';
        document.getElementById("card-trophies").style.height = '93%';
        document.getElementById('photo3').style.maxHeight = "50%";
        document.getElementById('trophies-button').textContent = 'Read less';
        document.getElementsByClassName('card-body')[2].style.display = 'flex';
    } else {
        // Възстановяване на оригиналните стилове
        document.getElementById("smallClubInfo").style.display='block';
        document.querySelector('.info-trophies').style.display = 'none';
        document.getElementById("card-trophies").style.minWidth = cardFansMinWidth;
        document.getElementById("card-trophies").style.height = cardFansHeight;
        document.getElementById('photo3').style.maxHeight = photoMaxHeight;
        document.getElementById('trophies-button').textContent = 'Read more';
        document.getElementsByClassName('card-body')[2].style.display = cardBodyDisplay;
        document.getElementById("card-fans").style.display = 'flex';
        document.getElementById("card-history").style.display = 'flex';
        document.getElementById("card-trophies").style.minWidth = '20%';
        document.getElementById("card-trophies").style.height = '73%';
    }});
    //------------------------------------------------------------------------------------------------------------------------------------------------------
document.getElementById('history-button').addEventListener('click', function() {
    if (document.getElementById('history-button').textContent === 'Read more') {
        document.getElementById("smallClubInfo").style.display='none';
        // Запазване на оригиналните стойности преди да ги промените
        var cardHistoryDisplay = document.getElementById("card-fans").style.display;
        var cardTrophiesDisplay = document.getElementById("card-trophies").style.display;
        var cardFansMinWidth = document.getElementById("card-history").style.minWidth;
        var cardFansHeight = document.getElementById("card-history").style.height;
        var photoMaxHeight = document.getElementById('photo1').style.maxHeight;
        var cardBodyDisplay = document.getElementsByClassName('card-body')[0].style.display;

        // Промяна на стиловете
        document.querySelector('.info-history').style.display = 'flex';
        document.getElementById("card-fans").style.display = 'none';
        document.getElementById("card-trophies").style.display = 'none';
        document.getElementById("card-history").style.minWidth = '60%';
        document.getElementById("card-history").style.height = '93%';
        document.getElementById('photo1').style.maxHeight = "50%";
        document.getElementById('history-button').textContent = 'Read less';
        document.getElementsByClassName('card-body')[0].style.display = 'flex';
    } else {
        document.getElementById("smallClubInfo").style.display='block';
        // Възстановяване на оригиналните стилове
        document.querySelector('.info-history').style.display = 'none';
        document.getElementById("card-history").style.minWidth = cardFansMinWidth;
        document.getElementById("card-history").style.height = cardFansHeight;
        document.getElementById('photo1').style.maxHeight = photoMaxHeight;
        document.getElementById('history-button').textContent = 'Read more';
        document.getElementsByClassName('card-body')[0].style.display = cardBodyDisplay;
        document.getElementById("card-fans").style.display = 'flex';
        document.getElementById("card-trophies").style.display = 'flex';
        document.getElementById("card-history").style.minWidth = '20%';
        document.getElementById("card-history").style.height = '73%';
    }});