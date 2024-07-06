//ToDo
this.document.getElementById('teamsPages').addEventListener('click', function(e){
    if(document.getElementById('teams-logos').style.display==='none'){
        document.getElementById('teams-logos').classList.toggle("show-ul");
    }else{
        document.getElementById('teams-logos').style.display = 'none';
    }
})