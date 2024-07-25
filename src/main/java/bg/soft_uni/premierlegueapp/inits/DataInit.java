package bg.soft_uni.premierlegueapp.inits;

import bg.soft_uni.premierlegueapp.exceptions.ResourceNotFoundException;
import bg.soft_uni.premierlegueapp.models.entities.*;
import bg.soft_uni.premierlegueapp.models.entities.enums.CompetitionNames;
import bg.soft_uni.premierlegueapp.models.entities.enums.RoleNames;
import bg.soft_uni.premierlegueapp.models.entities.enums.TeamNames;
import bg.soft_uni.premierlegueapp.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInit implements CommandLineRunner {
    private final ColorRepository colorRepository;
    private final CountryRepository countryRepository;
    private final TownRepository townRepository;
    private final CompetitionRepository competitionRepository;
    private final TeamRepository teamRepository;
    private final RoleRepository roleRepository;
    private final ClubSocialMediaRepository clubSocialMediaRepository;

    public DataInit(RoleRepository roleRepository, ColorRepository colorRepository, CountryRepository countryRepository, TownRepository townRepository, CompetitionRepository competitionRepository, TeamRepository teamRepository, ClubSocialMediaRepository clubSocialMediaRepository) {
        this.roleRepository = roleRepository;
        this.colorRepository = colorRepository;
        this.countryRepository = countryRepository;
        this.townRepository = townRepository;
        this.competitionRepository = competitionRepository;
        this.teamRepository = teamRepository;
        this.clubSocialMediaRepository = clubSocialMediaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.count()==0){
            Arrays.stream(RoleNames.values())
                    .map(Role::new)
                    .forEach(this.roleRepository::save);
        }

        if (colorRepository.count() == 0) {
            colorRepository.saveAll(List.of(
                    new Color("Red"),
                    new Color("Blue"),
                    new Color("Green"),
                    new Color("Yellow"),
                    new Color("Black"),
                    new Color("White")
            ));
        }

        if (countryRepository.count() == 0) {
            countryRepository.save(new Country("England"));
        }

        if (townRepository.count() == 0) {
            Country england = countryRepository.getByName("England");
            townRepository.saveAll(List.of(
                    new Town("London", england),
                    new Town("Leicester", england),
                    new Town("Nottingham", england),
                    new Town("Southampton", england),
                    new Town("Liverpool", england),
                    new Town("Manchester", england),
                    new Town("Brentford", england),
                    new Town("Brighton", england),
                    new Town("Wolverhampton", england),
                    new Town("Ipswich", england),
                    new Town("Birmingham", england),
                    new Town("Bournemouth", england),
                    new Town("Everton", england),
                    new Town("Newcastle upon Tyne", england)
            ));
        }

        if (competitionRepository.count() == 0) {
            competitionRepository.save(new Competition(CompetitionNames.PremierLeague, "https://www.youtube.com/embed/UFXRlCgykrw?si=Q17z4d3uezOOTzOf&autoplay=1", "/images/logos/imgbin_premier-league-uefa-champions-league-manchester-city-f-c-trophy-leicester-city-f-c-png.png"));
        }

        if (teamRepository.count() == 0) {
            Town london = townRepository.getByName("London");
            Town leicester = townRepository.getByName("Leicester");
            Town nottingham = townRepository.getByName("Nottingham");
            Town southampton = townRepository.getByName("Southampton");
            Town liverpool = townRepository.getByName("Liverpool");
            Town manchester = townRepository.getByName("Manchester");
            Town brentford = townRepository.getByName("Brentford");
            Town brighton = townRepository.getByName("Brighton");
            Town wolverhampton = townRepository.getByName("Wolverhampton");
            Town ipswich = townRepository.getByName("Ipswich");
            Town birmingham = townRepository.getByName("Birmingham");
            Town bournemouth = townRepository.getByName("Bournemouth");
            Town everton = townRepository.getByName("Everton");
            Town newCastle = townRepository.getByName("Newcastle upon Tyne");
            Color red = colorRepository.getByName("Red");
            Color blue = colorRepository.getByName("Blue");
            Color white = colorRepository.getByName("White");
            Color black = colorRepository.getByName("Black");
            Color yellow = colorRepository.getByName("Yellow");
            Competition competition = this.competitionRepository.getByName(CompetitionNames.PremierLeague);
            teamRepository.saveAll(List.of(
                    new Team(
                            competition,
                            TeamNames.Arsenal,  // name
                            "ARS",  // initials
                            LocalDate.of(1886, 1, 1),  // created
                            100000000,  // budget
                            "The History of Arsenal Football Club is one of the most illustrious and captivating narratives in the realm of football. Founded in 1886, Arsenal has amassed a plethora of titles and commendations, solidifying its standing among the English football elite. From its humble beginnings in Woolwich, South London, to its evolution into a global footballing powerhouse.",  // historySmallInfo
                            "The club proudly calls one of the world's most iconic stadiums home - Highbury, a place where legions of fans convene to rally behind their team in every encounter.\n\nThroughout the years, Arsenal has forged a legendary reputation as one of the most triumphant and influential names in football annals. Garnering multiple titles in the English Premier League, including numerous league championships and cups, Arsenal has etched its name into the fabric of English football history.\n\nYet, Arsenal's true eminence shines brightest on the European stage. With a distinctive brand of play and resilience in the face of challenges, Arsenal has secured multiple European trophies and other prestigious honors.\n\nThe club is celebrated for its iconic footballers who have proudly sported the Arsenal colors, as well as its visionary leaders who have left an indelible imprint on the global football landscape.",  // historyBigInfo
                            "The fans of Arsenal Football Club, affectionately known as the 'Gooners', play a pivotal role in shaping the club's identity and triumphs. Revered for their unwavering dedication, fervent loyalty, and vocal backing, Arsenal fans ignite an electric atmosphere at every match, transforming the Emirates Stadium into a fortress.",  // fansSmallInfo
                            "In victory or defeat, Arsenal supporters stand shoulder to shoulder with their team, celebrating triumphs and weathering setbacks with equal passion. Their chants echo through the stands, their songs reverberate around the stadium, and their banners wave proudly, inspiring players and unsettling adversaries alike.\n\nThe connection between Arsenal and its fans transcends boundaries of geography and time. Whether at home or on the road, Gooners travel far and wide to rally behind their beloved club, painting stadiums red wherever they roam.\n\nBut being an Arsenal fan is more than just supporting a football team; it's about camaraderie, identity, and a shared love for the game. Gooners embody the spirit of North London - resilient, proud, and full of passion.\n\nArsenal and its fans share an unbreakable bond, fueled by their devotion to the game and pursuit of glory, ensuring the club's enduring legacy.",  // fansBigInfo
                            "Arsenal Football Club boasts an illustrious trophy cabinet, adorned with a myriad of titles and honors that underscore its status as one of football's most successful institutions. Since its inception, Arsenal has amassed a formidable array of domestic and international trophies, firmly establishing its place in the annals of the sport.",  // trophiesSmallInfo
                            "Foremost among Arsenal's trophy collection are its league championships, with the club clinching numerous titles in the English top-flight. From the golden era of the 1930s to modern triumphs, Arsenal's league successes epitomize the club's enduring legacy and winning ethos.\n\nIn addition to league triumphs, Arsenal has enjoyed remarkable success in domestic cup competitions, including the FA Cup and the League Cup. These prestigious cups have provided countless moments of jubilation for players and fans alike, with unforgettable victories etched into the club's storied history.\n\nYet, it is Arsenal's feats in European competitions that truly distinguish it on the global stage.",  // trophiesBigInfo
                            red,  // kitColor
                            london  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.Chelsea,  // name
                            "CHE",  // initials
                            LocalDate.of(1905, 1, 1),  // created
                            120000000,  // budget
                            "The history of Chelsea Football Club is a tale woven with passion, perseverance, and an unwavering commitment to excellence. Established in 1905 in West London, the club swiftly emerged as a powerhouse in the footballing world, its influence extending far beyond the boundaries of the pitch.",  // historySmallInfo
                            "From its inception, Chelsea harbored ambitions of ascending to the summit of English football. Over the decades, the club's journey has been marked by notable successes and defining moments, as it etched its name into the annals of the sport's history.\n\nYet, Chelsea's narrative transcends mere victories and accolades. At its core lies a fervent and devoted fanbase, whose unwavering support serves as the heartbeat of the club. Through triumphs and trials alike, Chelsea fans have stood by their team, their passion resonating throughout Stamford Bridge and beyond.\n\nAs Chelsea Football Club continues to evolve and chart its course in the ever-changing landscape of football, its story remains a testament to the enduring spirit of determination, unity, and the pursuit of greatness.",  // historyBigInfo
                            "The fans of Chelsea Football Club are more than just supporters; they are the lifeblood of the club, infusing every match with passion, energy, and unwavering devotion. From the stands of Stamford Bridge to away matches across the country, Chelsea supporters create an electric atmosphere that fuels the players on the pitch.",  // fansSmallInfo
                            "Renowned for their loyalty and dedication, Chelsea fans stand by their team through every twist and turn of the season, celebrating victories and rallying behind the players in times of challenge. Their chants echo through the stadium, their colors adorn the stands, and their unwavering support inspires players to give their all on the field.\n\nThe bond between Chelsea Football Club and its fans runs deep, transcending the boundaries of the football pitch. Whether at home or on the road, Chelsea supporters travel far and wide to show their allegiance, creating a sea of blue wherever they go.\n\nBeing a Chelsea fan is more than just supporting a football club; it's about community, camaraderie, and shared experiences. The Blues faithful embody the spirit of London - diverse, passionate, and full of pride.",  // fansBigInfo
                            "Chelsea Football Club epitomizes a storied legacy of triumphs, adorned with a dazzling collection of trophies that illuminate its illustrious history. Since its founding, Chelsea has accumulated a wealth of domestic and international silverware, firmly cementing its place as one of the most distinguished institutions in English football.",  // trophiesSmallInfo
                            "Chelsea Football Club epitomizes a storied legacy of triumphs, adorned with a dazzling collection of trophies that illuminate its illustrious history.\n\nAt the heart of Chelsea's trophy haul are its Premier League championships, a testament to the club's enduring dominance on the domestic stage. From its early triumphs to historic title runs, Chelsea's league successes epitomize its relentless pursuit of excellence and unwavering determination.\n\nIn addition to its league conquests, Chelsea has left an indelible mark on various cup competitions, including the prestigious FA Cup and League Cup. These revered tournaments have provided countless moments of glory for players and fans alike, each victory etching itself into the fabric of the club's storied legacy.\n\nYet, it's Chelsea's triumphs on the European stage that truly distinguish it among footballing giants. The crowning jewel in Chelsea's collection came in 2012 when they lifted the UEFA Champions League, etching their name in footballing folklore and solidifying their status as a European powerhouse.",  // trophiesBigInfo
                            blue,  // kitColor
                            london  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.LeicesterCity,  // name
                            "LEI",  // initials
                            LocalDate.of(1884, 1, 1),  // created
                            80000000,  // budget
                            "Leicester City Football Club, founded in 1884, has a storied history marked by remarkable achievements and resilience. Despite facing numerous challenges and fluctuations in league positions over the years, Leicester City reached unprecedented heights in the 2015-2016 season, capturing the Premier League title in a stunning display of determination and teamwork.",  // historySmallInfo
                            "In addition to its historic Premier League triumph, Leicester City has enjoyed success in domestic cup competitions, including multiple victories in the FA Cup, with the most recent triumph coming in 2021. These cup successes have further enhanced the club's reputation and added to its rich legacy in English football.\n\nOff the field, Leicester City is deeply ingrained in the local community, engaging fans through various community initiatives and outreach programs. The club's commitment to its supporters fosters a strong sense of belonging and unity among fans, transcending the boundaries of the football pitch.\n\nOverall, Leicester City's journey exemplifies resilience, passion, and the ability to overcome adversity. From its humble beginnings to its status as a Premier League champion, the club continues to inspire fans worldwide, embodying the spirit and resilience of the city it represents.",  // historyBigInfo
                            "Leicester City Football Club boasts a passionate and devoted fanbase, known for their unwavering support and fervent loyalty through thick and thin. From the iconic King Power Stadium to away matches across the country, Leicester City fans create an electric atmosphere that fuels the players on the pitch.",  // fansSmallInfo
                            "Win, lose, or draw, Leicester City supporters stand by their team with pride, celebrating victories and rallying behind the players during challenging times. Their chants reverberate through the stands, their colors adorn the stadium, and their unwavering support inspires the team to give their all on the field.\n\nThe bond between Leicester City and its fans runs deep, transcending the boundaries of the football pitch. Whether at home or on the road, Foxes fans travel far and wide to show their allegiance, creating a sea of blue wherever they go.\n\nBeing a Leicester City fan is more than just supporting a football club; it's about camaraderie, passion, and a shared love for the game. The Foxes faithful embody the spirit of Leicester - resilient, proud, and full of passion. Their unwavering support fuels the team's ambition and ensures that the legacy of Leicester City continues to thrive.",  // fansBigInfo
                            "Leicester City Football Club has a distinguished trophy cabinet, punctuated by notable achievements that reflect its rise to prominence in English football. While historically not as extensive as some of its counterparts, Leicester City's recent successes have brought significant silverware to the club.",  // trophiesSmallInfo
                            "Foremost among Leicester City's trophy collection is their historic Premier League title triumph in the 2015-2016 season, a remarkable feat that defied all expectations. Widely regarded as one of the greatest underdog stories in sporting history, Leicester City's Premier League triumph captured the imagination of football fans worldwide and cemented their place in football folklore.\n\nIn addition to their Premier League success, Leicester City has also won the Football League Cup, lifting the trophy on three occasions in 1964, 1997, and 2000. These triumphs in domestic cup competitions have added to the club's rich history and provided moments of joy for players and fans alike.\n\nWhile Leicester City's trophy haul may not match some of the more decorated clubs in English football, their recent achievements have firmly established them as a force to be reckoned with. With ambitions of further success on both domestic and European fronts, Leicester City continues to build upon its legacy and strive for greatness in the footballing world.",  // trophiesBigInfo
                            blue,  // kitColor
                            leicester  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.NottinghamForest,  // name
                            "NFFC",  // initials
                            LocalDate.of(1865, 1, 1),  // created
                            70000000,  // budget
                            "The history of Nottingham Forest Football Club is a saga filled with glory, determination, and a relentless pursuit of excellence. Founded in 1865 in Nottingham, England, the club quickly rose to prominence in the footballing world, leaving an indelible mark on the sport.",  // historySmallInfo
                            "From its humble beginnings, Nottingham Forest harbored lofty ambitions of conquering English football. Throughout its storied journey, the club has experienced both triumphs and setbacks, each chapter contributing to its rich tapestry of history. Under the legendary management of Brian Clough in the late 1970s and early 1980s, Nottingham Forest enjoyed unparalleled success, winning consecutive European Cups in 1979 and 1980, etching their name into footballing folklore.\n\nBut beyond the trophies and titles lies the beating heart of Nottingham Forest – its passionate and loyal fanbase. Through thick and thin, Forest supporters have stood by their beloved club, their unwavering dedication echoing through the stands of the City Ground and reverberating across the footballing world.",  // historyBigInfo
                            "The tale of Nottingham Forest Football Club is one steeped in history, loyalty, and a relentless pursuit of glory. Established in 1865 in the heart of Nottingham, England, the club swiftly rose to prominence, etching its name into the annals of footballing legend with each passing season.",  // fansSmallInfo
                            "Devotion and unwavering support define the Nottingham Forest faithful. From the raucous chants echoing through the City Ground to the sea of red adorning the stands, the passion of the fans fuels the team's spirit. Through highs and lows, triumphs and trials, the Forest faithful stand resolute, united in their love for the club.\n\nThe bond between Nottingham Forest and its supporters transcends mere fandom; it's a shared journey of triumphs, heartaches, and unforgettable moments. Whether home or away, the Nottingham faithful travel far and wide, painting every stadium red and white with their fervent support.\n\nBeing a Nottingham Forest fan is more than just cheering for a football team; it's a way of life, a sense of belonging to a community that spans generations. The Reds embody the spirit of Nottingham - resilient, proud, and fiercely determined.",  // fansBigInfo
                            "Nottingham Forest Football Club boasts a rich tapestry of triumphs, with a glittering array of trophies adorning its illustrious history. Since its inception, Forest has amassed a treasure trove of domestic and international silverware, firmly establishing itself as one of English football's most decorated institutions.",  // trophiesSmallInfo
                            "Nottingham Forest Football Club boasts a rich tapestry of triumphs, with a glittering array of trophies adorning its illustrious history.\n\nAt the heart of Nottingham Forest's trophy haul are its league championships, a testament to the club's enduring dominance on the domestic stage. From its early triumphs to historic title runs, Forest's league successes epitomize its relentless pursuit of excellence and unwavering determination.\n\nIn addition to its league conquests, Nottingham Forest has left an indelible mark on various cup competitions, including the prestigious FA Cup and League Cup. These revered tournaments have provided countless moments of glory for players and fans alike, each victory etching itself into the fabric of the club's storied legacy.\n\nYet, it's Nottingham Forest's triumphs on the European stage that truly distinguish it among footballing giants. The crowning jewel in Forest's collection came in 1979 and 1980 when they lifted the UEFA European Cup, etching their name in footballing folklore and solidifying their status as a European powerhouse.",  // trophiesBigInfo
                            red,  // kitColor
                            nottingham  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.Southampton,  // name
                            "SOU",  // initials
                            LocalDate.of(1885, 1, 1),  // created
                            60000000,  // budget
                            "The history of Southampton Football Club is a narrative steeped in dedication, resilience, and an enduring quest for achievement. Founded in 1885 in the port city of Southampton, the club swiftly rose to prominence in English football, its legacy marked by remarkable highs and formidable challenges.",  // historySmallInfo
                            "Southampton's journey through the footballing world has been characterized by both exhilarating triumphs and testing adversities. The club has experienced the glory of significant victories and the grit required to overcome setbacks, all while maintaining its distinctive identity. Central to this journey is Southampton's loyal and passionate fanbase, whose unwavering support has been a constant source of strength and inspiration.\n\nAt the core of Southampton FC lies a community united by their love for the club. The fans, with their enduring chants and vibrant presence, breathe life into St Mary's Stadium, creating an atmosphere that resonates far beyond the city. As Southampton continues to navigate the evolving landscape of football, its story remains a testament to the spirit of perseverance, unity, and the relentless pursuit of success.",  // historyBigInfo
                            "The supporters of Southampton Football Club are the beating heart of the team's success, their unwavering passion and dedication fueling the club's journey through triumphs and challenges alike. Since its founding in 1885, Southampton has cultivated a loyal and fervent fanbase that transcends geographical boundaries and generations.",  // fansSmallInfo
                            "Southampton's fans, known for their vibrant chants and unwavering loyalty, create an electrifying atmosphere at St Mary's Stadium, transforming it into a fortress for the team. Their presence resonates both on and off the pitch, inspiring players and uniting communities in celebration of the club's rich heritage.\n\nBeyond the confines of the stadium, Southampton's supporters embody the spirit of camaraderie and resilience, forming a tight-knit community bound by their love for the club. Through highs and lows, victories and defeats, the fans stand shoulder to shoulder, a testament to the enduring power of unity and passion in football.",  // fansBigInfo
                            "The trophy cabinet of Southampton Football Club boasts a rich tapestry of achievements, reflecting the club's storied history and unwavering commitment to excellence. Since its inception in 1885, Southampton has etched its name into the annals of footballing glory, securing an impressive array of accolades and honors.",  // trophiesSmallInfo
                            "Among Southampton's notable triumphs is their historic victory in the FA Cup, a momentous achievement that stands as a testament to the club's enduring legacy. Additionally, Southampton has showcased their prowess on the continental stage, with notable successes in European competitions further solidifying their reputation as a force to be reckoned with.\n\nIn addition to their on-field triumphs, Southampton's trophy cabinet also boasts domestic league titles and other prestigious honors, each representing a chapter in the club's illustrious history. These trophies serve as tangible reminders of Southampton's unwavering pursuit of success and their relentless dedication to achieving greatness on all fronts.",  // trophiesBigInfo
                            red,  // kitColor
                            southampton  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.Liverpool,  // name
                            "LIV",  // initials
                            LocalDate.of(1892, 1, 1),  // created
                            150000000,  // budget
                            "The History of Liverpool Football Club is one of the richest and most exhilarating in the world of football. Founded in 1892, Liverpool has garnered numerous titles and accolades, cementing its place among the elite of English football. Starting as a small club in the provincial city of Liverpool, it quickly gains worldwide fame and attracts millions of fans around the globe.",  // historySmallInfo
                            "The club is the proud host of one of the most iconic stadiums in the world - Anfield, where thousands of fans gather to support their team in every match.\n\nOver the years, Liverpool has built a legendary status as one of the most successful and influential names in football history. The club has clinched multiple titles in the English Premier League, including numerous league championships and cups.\n\nBut Liverpool's true glory lies in European competitions. With its classy style of play and the adversities it has overcome over the years, Liverpool has won multiple European Cups and other prestigious titles.\n\nThe club is renowned for its legendary footballers who have proudly donned the Liverpool colors, as well as its leaders who have left an indelible mark on the history of world football.\n\nWith the tradition, prestige, and passion that define it, Liverpool continues to inspire its fans and the football community worldwide. The history of Liverpool is a story of perseverance, ambition, and relentless pursuit of excellence.",  // historyBigInfo
                            "The fans of Liverpool Football Club, often referred to as the 'Red Army' or the 'Kopites', are an integral part of the club's identity and success. Renowned for their unwavering passion, loyalty, and vocal support, Liverpool fans create an electrifying atmosphere at every match, turning Anfield into a fortress.",  // fansSmallInfo
                            "Through thick and thin, Liverpool supporters stand by their team, celebrating victories and enduring defeats with equal fervor. Their chants, songs, and banners adorn the stands, inspiring players and intimidating opponents alike.\n\nThe bond between Liverpool and its fans runs deep, transcending geographical boundaries and generations. Whether at home or away, Liverpool fans travel far and wide to support their beloved club, creating a sea of red wherever they go.\n\nBut it's not just about football for Liverpool fans; it's about community, belonging, and shared passion. They embody the spirit of the city - resilient, proud, and full of heart.\n\nTogether, Liverpool and its fans form an unbreakable bond, united by a love for the game and a desire for glory. Their unwavering support fuels the club's success and ensures that the spirit of Liverpool FC lives on, season after season.",  // fansBigInfo
                            "Liverpool Football Club boasts a glittering trophy cabinet, adorned with numerous titles and accolades that attest to its status as one of the most successful clubs in football history. Since its inception, Liverpool has amassed a formidable collection of domestic and international trophies, etching its name into the annals of the sport.",  // trophiesSmallInfo
                            "At the forefront of Liverpool's trophy haul are its league championships, with the club having secured numerous titles in the English top-flight. From the early days of dominance in the 1970s and 1980s to more recent triumphs, Liverpool's league successes showcase the club's enduring excellence and winning mentality.\n\nIn addition to league championships, Liverpool has enjoyed remarkable success in domestic cup competitions, including the FA Cup and the League Cup. These cups have provided moments of joy and glory for players and fans alike, with memorable victories etched into the club's rich tapestry of history.\n\nHowever, it is Liverpool's exploits in European competitions that truly set it apart on the global stage.",  // trophiesBigInfo
                            red,  // kitColor
                            liverpool  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.CrystalPalace,  // name
                            "CP",  // initials
                            LocalDate.of(1905, 1, 1),  // created
                            80000000,  // budget
                            "The football club Crystal Palace has a long and storied history, serving as an integral part of English football. Founded in 1905, the team has grown into an iconic symbol for the London area and the footballing community.",  // historySmallInfo
                            "Over the years, Crystal Palace has experienced many thrilling moments, including periods of success and challenges. With its classy style of play and the dedication of its players, Crystal Palace has earned respect and admiration for the game.\n\nThe supporters of Crystal Palace are as crucial as the team itself. Their passionate backing and loyalty have created an incredible atmosphere at Selhurst Park and away matches, making every Crystal Palace game unforgettable.\n\nWhile Crystal Palace may not have amassed many trophies, the team remains a significant part of London's football history and heritage. With reverence for the game and a commitment to success, Crystal Palace continues to inspire its fans and remain a prominent player in the world of English football.",  // historyBigInfo
                            "The supporters of Crystal Palace Football Club, known for their unwavering dedication and vibrant spirit, are an essential aspect of the club's identity. From the stands of Selhurst Park to the far reaches of away matches, Palace fans create an electrifying atmosphere that ignites the passion of players and fans alike.",  // fansSmallInfo
                            "Renowned for their fervent support and loyalty, Crystal Palace fans stand by their team through thick and thin, celebrating victories and rallying behind the players during challenging times. Their chants, songs, and colorful displays adorn the stadium, showcasing their unwavering commitment to the club.\n\nThe bond between Crystal Palace and its fans runs deep, transcending mere spectatorship to embody a shared sense of community and belonging. Whether at home or on the road, Palace supporters travel far and wide to show their dedication, creating a sea of red and blue wherever they go.\n\nBeing a Crystal Palace fan is more than just supporting a football club; it's about camaraderie, passion, and a shared love for the game. Together, the players and fans of Crystal Palace forge an unbreakable bond, united in their pursuit of success and their unwavering belief in the club's values.",  // fansBigInfo
                            "Crystal Palace Football Club has a storied history marked by notable achievements, both domestically and in lower league competitions. Since its establishment, the club has experienced moments of triumph and success, etching its name in the annals of English football.",  // trophiesSmallInfo
                            "Among Crystal Palace's notable accomplishments are its successes in lower league competitions, including two first-place finishes in the Second Division (Championship). These achievements highlight the club's ability to compete at a high level and secure promotion to the top tier of English football.\n\nWhile Crystal Palace may not have amassed a significant number of major trophies, its legacy is characterized by resilience, determination, and a commitment to excellence on the pitch. The club's dedication to nurturing talent and fostering a competitive spirit has earned it respect within the footballing community.\n\nDespite the absence of major silverware, Crystal Palace's journey is defined by its ability to overcome challenges and compete at the highest level of English football. With each season, the club continues to strive for success, driven by the passion and unwavering support of its loyal fans.",  // trophiesBigInfo
                            blue,  // kitColor
                            london  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.AstonVilla,  // name
                            "AVFC",  // initials
                            LocalDate.of(1874, 1, 1),  // created
                            90000000,  // budget
                            "The History of Aston Villa Football Club is a tale steeped in tradition, tracing its roots back to 1874. Situated in Birmingham, Aston Villa stands as one of England's oldest and most esteemed football clubs, boasting a legacy defined by triumphs, challenges, and enduring passion. From its humble origins to its rise as a prominent figure in English football, Aston Villa's journey reflects the spirit of determination and resilience.",  // historySmallInfo
                            "Founded by members of the Villa Cross Wesleyan Chapel cricket team, Aston Villa emerged from humble beginnings to become a prominent figure in the burgeoning world of football. Throughout its storied history, the club has played a pivotal role in shaping the landscape of English football, contributing to its growth and development.\n\nAston Villa's golden era dawned in the late 19th and early 20th centuries under the guidance of legendary manager George Ramsay. During this period, the club achieved unprecedented success, claiming numerous league titles and FA Cups, solidifying its reputation as a dominant force in English football.\n\nAs Aston Villa continues to build upon its rich heritage and tradition, the club remains a beloved institution in English football, cherished by fans and respected by rivals alike. With each passing season, Aston Villa's legacy grows, as it continues to inspire generations of players and fans with its commitment to excellence and the pursuit of glory.",  // historyBigInfo
                            "The devoted fans of Aston Villa Football Club, affectionately known as the 'Villans', play a pivotal role in shaping the club's identity and success. Revered for their unwavering dedication, fervent loyalty, and vocal support, Aston Villa fans create an electrifying atmosphere at every match, turning Villa Park into a fortress.",  // fansSmallInfo
                            "In victory or defeat, Aston Villa supporters stand united with their team, celebrating triumphs and enduring setbacks with equal passion. Their chants resonate throughout the stadium, their songs echoing the rich history of the club, and their banners proudly displaying the claret and blue colors inspire players and intimidate opponents alike.\n\nThe bond between Aston Villa and its fans transcends geographical boundaries and generations. Whether at home or away, Villans travel far and wide to support their beloved club, painting stadiums with claret and blue wherever they go. But being an Aston Villa fan is more than just supporting a football team; it's about community, identity, and a shared love for the game.\n\nAston Villa and its fans share an unbreakable bond, fueled by their devotion to the game and pursuit of glory, ensuring the club's enduring legacy.",  // fansBigInfo
                            "Aston Villa Football Club boasts a rich and proud trophy cabinet, adorned with a collection of titles and honors that underscore its esteemed status in the world of football. Throughout its illustrious history, Aston Villa has enjoyed success in both domestic and international competitions, firmly establishing its place among the elite clubs of the sport.",  // trophiesSmallInfo
                            "At the forefront of Aston Villa's trophy haul are its league championships, with the club securing numerous titles in the English top-flight. From its early triumphs to more recent successes, Aston Villa's league victories symbolize the club's enduring tradition of excellence. Additionally, Aston Villa has enjoyed notable triumphs in domestic cup competitions, including the FA Cup and the League Cup. These prestigious trophies have provided moments of joy and pride for players and fans alike, immortalizing the club's achievements in the annals of football history.\n\nAlthough Aston Villa's European exploits may not rival its domestic achievements, the club has made notable appearances and contributions on the continental stage, further enhancing its reputation as a distinguished football club.",  // trophiesBigInfo
                            blue,  // kitColor
                            birmingham  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.BournemouthAFC,  // name
                            "BAFC",  // initials
                            LocalDate.of(1899, 1, 1),  // created
                            50000000,  // budget
                            "Bournemouth AFC, founded in 1899, has a storied history marked by resilience, determination, and a commitment to progress. Despite facing challenges and setbacks along the way, the club has continually risen to the occasion, etching its name into the annals of English football.",  // historySmallInfo
                            "Throughout its journey, Bournemouth AFC has experienced both highs and lows, navigating various divisions and leagues with steadfast resolve. The club's ascent through the ranks reflects its unwavering dedication to success and its ability to overcome adversity, embodying the spirit of perseverance and unity.\n\nIn recent years, Bournemouth AFC has enjoyed a period of sustained success, marked by promotions and memorable achievements. Under the guidance of visionary leadership and the unwavering support of its fans, the club has firmly established itself as a formidable force in English football, earning plaudits for its style of play and competitive spirit.\n\nAs Bournemouth AFC continues to chart its course in the world of football, its rich history serves as a source of inspiration and pride for players, fans, and the community alike. With each passing season, the club reaffirms its commitment to excellence and its determination to leave a lasting legacy in the beautiful game.",  // historyBigInfo
                            "The fans of Bournemouth AFC, affectionately known as the \"Cherries,\" play an integral role in shaping the identity and spirit of the club. Known for their unwavering loyalty, passion, and unwavering support, Bournemouth's fans create a vibrant atmosphere at every match, transforming the Vitality Stadium into a fortress of noise and energy.",  // fansSmallInfo
                            "Whether cheering from the stands or following the team on the road, the Cherries faithful are a constant source of inspiration for players, motivating them to give their all on the pitch. Their chants, songs, and colorful displays create an electric atmosphere that resonates throughout the stadium, driving the team forward in times of triumph and adversity alike.\n\nThe connection between Bournemouth AFC and its fans runs deep, transcending the boundaries of the football pitch. United by their love for the club and their shared experiences, the Cherries faithful form a tight-knit community that celebrates victories and rallies together in times of challenge. Their unwavering support fuels the team's ambition and strengthens the bond between players, fans, and the club, ensuring that Bournemouth AFC continues to thrive both on and off the pitch.",  // fansBigInfo
                            "Bournemouth AFC has yet to secure major silverware in English football, having primarily competed in lower divisions for much of its history. While the club has experienced success in lower league competitions, including promotions and notable finishes, it has not claimed significant trophies on the national or international stage.",  // trophiesSmallInfo
                            "Despite this, Bournemouth has achieved significant milestones, such as winning the Football League Third Division title in the 1986–1987 season and the Football League Championship title in the 2014-2015 season. These triumphs underline the club's progress and resilience in the footballing landscape, showcasing its ability to compete and succeed in competitive leagues.\n\nAdditionally, Bournemouth has enjoyed notable cup runs in domestic tournaments, including the FA Cup and the League Cup, where it has faced top-tier opposition and made memorable appearances. While the club's trophy cabinet may not boast major honors, its achievements reflect its determination, spirit, and commitment to success in English football.",  // trophiesBigInfo
                            red,  // kitColor
                            bournemouth  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.Everton,  // name
                            "EFC",  // initials
                            LocalDate.of(1878, 1, 1),  // created
                            60000000,  // budget
                            "Everton Football Club boasts a rich and illustrious history that spans over a century, making it one of the most storied clubs in English football. Founded in 1878, Everton has played a significant role in shaping the landscape of the sport, both domestically and internationally.",  // historySmallInfo
                            "Throughout its history, Everton has been synonymous with success, capturing the hearts of football fans around the world with its attractive style of play and the remarkable achievements of its players. The club's iconic royal blue shirts and the renowned Goodison Park stadium serve as enduring symbols of its heritage and tradition.\n\nFrom the early days of pioneering football in the late 19th century to its golden era of the 1980s, Everton has left an indelible mark on the sport, producing legendary players and memorable moments that have become part of football folklore. The club's rich tapestry of history is woven with triumphs, challenges, and moments of sheer brilliance that have solidified its place among the elite of English football.\n\nWith a passionate fanbase that spans generations, Everton continues to uphold its legacy of excellence, striving for success on the pitch while remaining true to its values of integrity, unity, and community. As Everton looks toward the future, its illustrious past serves as a source of inspiration and pride, driving the club forward as it seeks to add new chapters to its storied history.",  // historyBigInfo
                            "The fans of Everton Football Club, affectionately known as the \"Toffees\" or the \"Blues,\" are an integral part of the club's identity and success. Renowned for their unwavering passion, loyalty, and vocal support, Everton fans create an electrifying atmosphere at every match, turning Goodison Park into a fortress.",  // fansSmallInfo
                            "Through thick and thin, Everton supporters stand by their team, celebrating victories and enduring defeats with equal fervor. Their chants, songs, and banners adorn the stands, inspiring players and intimidating opponents alike. The bond between Everton and its fans runs deep, transcending geographical boundaries and generations.\n\nWhether at home or away, Everton fans travel far and wide to support their beloved club, creating a sea of blue wherever they go. But it's not just about football for Evertonians; it's about community, belonging, and shared passion. They embody the spirit of the city of Liverpool - resilient, proud, and full of heart.\n\nTogether, Everton and its fans form an unbreakable bond, united by a love for the game and a desire for glory. Their unwavering support fuels the club's success and ensures that the spirit of Everton lives on, season after season.",  // fansBigInfo
                            "Everton Football Club has a decorated history, marked by numerous trophies and accolades that showcase its success and pedigree in English football. Since its inception, Everton has captured various titles and honors, cementing its place among the elite clubs of the sport.",  // trophiesSmallInfo
                            "The club's trophy cabinet includes league championships, with Everton clinching the English top-flight title on nine occasions. Additionally, Everton has lifted the FA Cup five times, contributing to its rich heritage and tradition in domestic cup competitions.\n\nIn European competitions, Everton has also left its mark, winning the European Cup Winners' Cup in the 1984-1985 season. This triumph on the continental stage further solidified Everton's reputation as a formidable force in European football.\n\nFurthermore, Everton has enjoyed success in other competitions, such as the FA Charity Shield, the League Cup, and various regional and minor trophies throughout its illustrious history.\n\nOverall, Everton's trophy haul reflects its enduring excellence and competitiveness on the footballing stage, serving as a testament to the club's rich legacy and the passion of its players and fans alike.",  // trophiesBigInfo
                            blue,  // kitColor
                            everton  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.ManchesterCity,  // name
                            "MCFC",  // initials
                            LocalDate.of(1880, 1, 1),  // created
                            80000000,  // budget
                            "Manchester City Football Club, founded in 1880, has forged a rich and varied history, marked by both triumphs and challenges. Over the years, the club has become a prominent figure in English football, achieving success across various competitions.",  // historySmallInfo
                            "With multiple league championships under its belt, Manchester City has consistently showcased its dominance in the English top-flight, establishing itself as a force to be reckoned with. Victories in prestigious domestic cup competitions, such as the FA Cup and the League Cup, have further solidified the club's status among football's elite.\n\nBeyond domestic success, Manchester City has made significant strides on the European stage, with notable appearances in competitions like the UEFA Champions League and UEFA Europa League. These ventures have contributed to the club's growing reputation as a global football powerhouse.\n\nThroughout its history, Manchester City's unwavering commitment to excellence and its ability to navigate through challenges have been central to its identity. With a resilient spirit and a relentless pursuit of success, the club continues to leave a lasting mark on the world of football, inspiring fans and players alike.",  // historyBigInfo
                            "Manchester City Football Club boasts a passionate and dedicated fanbase, known for their unwavering support and fervent loyalty. From the bustling stands of the Etihad Stadium to stadiums across the country, Manchester City fans create an electrifying atmosphere that fuels the team on matchdays.",  // fansSmallInfo
                            "Through thick and thin, these devoted supporters stand by their club, celebrating victories and rallying behind the team in times of adversity. Their chants echo through the stadium, their colors adorn the stands, and their unwavering allegiance inspires players to give their all on the pitch.\n\nThe bond between Manchester City and its fans runs deep, transcending geographical boundaries and generations. Whether at home or away, City fans travel far and wide to show their unwavering support, creating a sea of sky blue wherever they go.\n\nBeing a Manchester City fan is more than just supporting a football club; it's about belonging to a community, sharing experiences, and embracing the passion for the game. United by their love for the club, City fans embody the spirit of Manchester - resilient, proud, and full of determination. Together, they form an unbreakable bond that continues to drive Manchester City forward, season after season.",  // fansBigInfo
                            "Manchester City Football Club's trophy cabinet is adorned with numerous titles and accolades, underscoring its status as one of the most successful clubs in English football history.",  // trophiesSmallInfo
                            "The club has secured multiple league championships, including the English top-flight title on numerous occasions, showcasing its dominance domestically.\n\nIn addition to their league successes, Manchester City has triumphed in various domestic cup competitions, including the FA Cup and the League Cup. These victories have provided moments of joy and pride for players and fans alike, etching their names into the annals of football history.\n\nFurthermore, Manchester City's achievements extend to the European stage, with notable successes in the UEFA Champions League. While the club's European journey has seen its share of challenges, Manchester City has made significant strides in the competition, further solidifying its reputation as a force to be reckoned with on the continental stage.\n\nOverall, Manchester City's trophy haul reflects its sustained excellence and ambition, showcasing the club's commitment to success both domestically and internationally. With a blend of talent, determination, and unwavering support from its fans, Manchester City continues to etch its name in the annals of footballing glory.",  // trophiesBigInfo
                            blue,  // kitColor
                            manchester  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.TottenhamHotspur,  // name
                            "THFC",  // initials
                            LocalDate.of(1882, 9, 5),  // created
                            100000000,  // budget
                            "Tottenham Hotspur Football Club has a storied history dating back to its formation in 1882. Over the years, the club has established itself as one of the most iconic and respected teams in English football, with a legacy built on tradition, success, and a passionate fanbase.",  // historySmallInfo
                            "The club's early years were marked by modest achievements, but Tottenham's fortunes changed dramatically in the 20th century. Under the guidance of legendary manager Bill Nicholson, Spurs enjoyed a golden era in the 1960s, winning numerous domestic titles, including the First Division and the FA Cup.\n\nTottenham's success continued into the modern era, with the club consistently challenging for silverware both domestically and in European competitions. The team's famous \"Glory, Glory, Tottenham Hotspur\" motto epitomizes the club's ambition and determination to succeed at the highest level.\n\nOff the pitch, Tottenham has always been at the forefront of innovation and progress, with a state-of-the-art stadium and world-class training facilities. The club's commitment to excellence extends beyond the football pitch, with a strong emphasis on community engagement and social responsibility.\n\nAs Tottenham Hotspur looks to the future, the club remains dedicated to its core values of attacking football, youth development, and success on all fronts.",  // historyBigInfo
                            "Tottenham Hotspur Football Club boasts a passionate and devoted fanbase that forms an integral part of the club's identity and success. Affectionately known as the \"Spurs faithful,\" these supporters bring energy, enthusiasm, and unwavering loyalty to every match, creating an electrifying atmosphere at Tottenham Hotspur Stadium and beyond.",  // fansSmallInfo
                            "Through thick and thin, Tottenham fans stand by their team, rallying behind the players with chants, songs, and unwavering support. Their dedication is palpable, whether at home matches or on the road, as they travel far and wide to cheer on their beloved Spurs, painting stadiums in the club's iconic white and navy colors.\n\nThe bond between Tottenham Hotspur and its fans runs deep, transcending geographical boundaries and generations. It's more than just football for the Spurs faithful; it's about community, camaraderie, and shared passion. They embody the spirit of North London - resilient, proud, and full of heart.\n\nTogether, Tottenham Hotspur and its fans form an unbreakable bond, united by a love for the game and a desire for glory. Their unwavering support fuels the team's ambition and ensures that the spirit of Spurs lives on, season after season.",  // fansBigInfo
                            "Tottenham Hotspur Football Club boasts a rich history filled with numerous trophies and accolades, solidifying its status as a powerhouse in English football.",  // trophiesSmallInfo
                            "Beyond domestic success, Tottenham Hotspur has made significant strides in European competitions, clinching the UEFA Cup (now UEFA Europa League) twice and reaching the final of the UEFA Champions League. These achievements showcase the club's ambition and its ability to compete at the highest levels on the continental stage.\n\nMoreover, Tottenham Hotspur's trophy cabinet includes various other honors, including four League Cup triumphs and several FA Charity Shield victories. With a rich tapestry of achievements spanning decades, Spurs continue to inspire generations of fans worldwide with their enduring excellence and commitment to success.",  // trophiesBigInfo
                            white,  // kitColor
                            london  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.Brentford,  // name
                            "BFC",  // initials
                            LocalDate.of(1889, 10, 10),  // created
                            50000000,  // budget
                            "Established in 1889, Brentford Football Club boasts a storied past deeply intertwined with the fabric of English football. Situated in West London, the club's journey is a testament to resilience, determination, and a relentless pursuit of success.",  // historySmallInfo
                            "From its modest origins, Brentford swiftly ascended the ranks of English football, navigating various leagues and divisions with grit and determination. The early years were marked by fierce local rivalries and spirited competition, laying the groundwork for the club's enduring legacy.\n\nBrentford experienced its heyday in the 1930s, a period defined by notable success in domestic competitions. Under the astute leadership of managers like Harry Curtis, the club enjoyed memorable cup runs and achieved remarkable feats, captivating the hearts of fans.\n\nDespite facing challenges and setbacks along the way, Brentford's spirit remained unyielding. The club adapted to changing times, embracing new opportunities while staying true to its values and traditions.\n\nIn recent years, Brentford has embarked on a new chapter, characterized by a commitment to progressive football and innovative club development. With state-of-the-art facilities and a passionate fanbase, Brentford stands poised for a bright future, symbolizing the enduring spirit of the beautiful game.",  // historyBigInfo
                            "The fans of Brentford Football Club, affectionately known as the 'Bees', are the heartbeat of the club, infusing every match with passion, energy, and unwavering support. From the terraces of Griffin Park to the new Brentford Community Stadium, the Bees faithful create an electric atmosphere that propels the team forward.",  // fansSmallInfo
                            "Renowned for their loyalty and dedication, Brentford fans stand by their team through thick and thin, celebrating victories and rallying behind the players in times of adversity. Their chants echo through the stands, their colors adorn the stadium, and their unwavering support inspires players to give their all on the pitch.\n\nThe bond between Brentford and its fans runs deep, transcending the boundaries of the football pitch. Whether at home or on the road, Bees supporters travel far and wide to show their allegiance, creating a sea of red and white wherever they go.\n\nBeing a Brentford fan is more than just supporting a football club; it's about community, camaraderie, and shared experiences. The Bees faithful embody the spirit of West London - resilient, passionate, and proud.\n\nTogether, Brentford and its fans form an unbreakable bond, united in their love for the game and their belief in the club's potential.",  // fansBigInfo
                            "Brentford Football Club, while not boasting a glittering trophy cabinet like some of its counterparts, has a rich history filled with moments of triumph, resilience, and community spirit. Founded in 1889, the club's focus has often been on its passionate fanbase, grassroots development, and the pursuit of excellence on the pitch.",  // trophiesSmallInfo
                            "Although Brentford has not secured major trophies in domestic or international competitions, the club has enjoyed success in lower league tournaments and has consistently demonstrated its commitment to nurturing talent and promoting a brand of attractive, attacking football. Brentford's legacy is not solely defined by silverware, but rather by its ability to unite communities, inspire young players, and embody the spirit of football as a unifying force.\n\nIn addition to its achievements, Brentford has clinched the championship in the Second Division (Championship) during the seasons 1934/1935 and 2020/2021. These triumphs underscore the club's resilience and determination to compete at the highest level, despite not securing major trophies in the top leagues or national cups.\n\nWith a dedicated fanbase and a forward-thinking approach, Brentford continues to strive for success, eager to etch its name in the annals of football history.",  // trophiesBigInfo
                            red,  // kitColor
                            brentford  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.Fulham,  // name
                            "FFC",  // initials
                            LocalDate.of(1879, 6, 30),  // created
                            40000000,  // budget
                            "Fulham Football Club boasts a storied history, defined by moments of triumph and resilience that have shaped its legacy in English football. Founded in 1879, Fulham has forged its path through the ranks of the footballing world, leaving an indelible mark on the sport.",  // historySmallInfo
                            "Though not adorned with as many trophies as some of its counterparts, Fulham's journey is characterized by its unwavering spirit and dedication to the game. Over the years, the club has showcased its talent and determination, earning respect both domestically and internationally.\n\nWhile Fulham may not have secured top-flight league titles, its presence in English football is undeniable. The club has had notable successes in cup competitions, including memorable runs in the FA Cup and the League Cup, demonstrating its ability to compete at the highest levels.\n\nBeyond domestic competitions, Fulham has also made appearances in European tournaments, showcasing its quality on the continental stage. Despite facing challenges along the way, Fulham continues to strive for excellence, driven by the passion of its players and the unwavering support of its loyal fanbase.\n\nIn essence, Fulham's history is a testament to the club's resilience, determination, and enduring legacy in the world of football.",  // historyBigInfo
                            "The fans of Fulham Football Club are an integral part of the club's identity and success, contributing to its vibrant atmosphere and unwavering support. Known for their passion and loyalty, Fulham fans create an electrifying atmosphere at Craven Cottage and beyond, standing by their team through every twist and turn of the season.",  // fansSmallInfo
                            "From the stands, Fulham supporters cheer on their beloved club with fervor, inspiring players and creating an atmosphere that fuels the team's performance on the pitch. Their chants and songs echo throughout the stadium, creating a sense of unity and belonging among fellow fans.\n\nRegardless of the results on the field, Fulham fans remain dedicated to their club, celebrating victories and rallying behind the team during challenging times. Their unwavering support is a testament to the strong bond between the club and its supporters, transcending geographical boundaries and generations.\n\nWhether at home or away, Fulham fans travel far and wide to show their allegiance, painting stadiums with the club's colors and creating a sea of support wherever they go.",  // fansBigInfo
                            "Fulham Football Club, while not boasting a plethora of major trophies, has enjoyed success in various competitions throughout its history, demonstrating its competitive spirit and resilience on the footballing stage.",  // trophiesSmallInfo
                            "Among Fulham's notable achievements are its triumphs in lower league competitions, including the Football League Second Division title on two occasions. These victories underscore the club's ability to compete at different levels of English football and its commitment to excellence across all competitions.\n\nIn addition to its successes in lower leagues, Fulham has also made significant appearances in domestic cup competitions. Although the club has not lifted the FA Cup or the League Cup, its presence in these tournaments highlights its competitive edge and determination to succeed on the national stage.\n\nWhile Fulham's trophy cabinet may not rival those of some of its counterparts, the club's achievements in lower league competitions and its competitive performances in domestic cups reflect its rich history and tradition in English football. These accomplishments serve as a source of pride for players, fans, and the wider Fulham community, embodying the spirit of the club and its enduring legacy.",  // trophiesBigInfo
                            black,  // kitColor
                            london  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.ManchesterUnited,  // name
                            "MUFC",  // initials
                            LocalDate.of(1878, 12, 5),  // created
                            70000000,  // budget
                            "Manchester United Football Club, founded in 1878, stands as a towering symbol of footballing excellence, boasting a rich legacy that spans over a century. From its humble beginnings, the club has grown into a global powerhouse, revered for its success and tradition in English football.",  // historySmallInfo
                            "Over the years, Manchester United has clinched a multitude of league championships, firmly establishing its dominance in the English top-flight. These titles serve as a testament to the club's enduring pursuit of excellence and winning mentality, ingrained in its DNA since its inception.\n\nIn addition to their domestic conquests, Manchester United has left an indelible mark on prestigious cup competitions such as the FA Cup and the League Cup. These triumphs have provided unforgettable moments for players and fans alike, contributing to the club's storied history.\n\nManchester United's legacy extends beyond domestic glory to the European stage, where it has captured the UEFA Champions League trophy on multiple occasions. These European triumphs have solidified the club's status as a global footballing giant and further enhanced its reputation on the international stage.\n\nOverall, Manchester United's history is a saga of triumph, resilience, and unwavering passion, encapsulating the essence of footballing greatness. As the club continues to evolve and inspire, its legacy remains an enduring symbol of sporting excellence and tradition.",  // historyBigInfo
                            "The fans of Manchester United Football Club, affectionately known as the 'Red Devils', are the heartbeat of the club, infusing every match with passion, loyalty, and unwavering support. From the stands of Old Trafford to stadiums across the globe, the Red Devils faithful create an electrifying atmosphere that fuels the players on the pitch.",  // fansSmallInfo
                            "Renowned for their fervent dedication, Manchester United fans stand by their team through thick and thin, celebrating victories and enduring setbacks with equal fervor. Their chants echo through the stadium, their colors adorn the stands, and their unwavering support inspires players to give their all on the field.\n\nThe bond between Manchester United and its fans runs deep, transcending geographical boundaries and generations. Whether at home or on the road, Red Devils supporters travel far and wide to show their allegiance, creating a sea of red wherever they go.\n\nBeing a Manchester United fan is more than just supporting a football club; it's about camaraderie, identity, and a shared love for the game. The Red Devils faithful embody the spirit of Manchester - resilient, proud, and full of passion.",  // fansBigInfo
                            "Manchester United Football Club boasts a storied trophy cabinet, adorned with numerous titles and accolades that underscore its status as one of the most successful clubs in football history.",  // trophiesSmallInfo
                            "At the forefront of Manchester United's trophy haul are its league championships, with the club having clinched the English top-flight title on multiple occasions. Additionally, Manchester United has lifted the FA Cup numerous times, contributing to its rich heritage and tradition in domestic cup competitions.\n\nIn European competitions, Manchester United has left an indelible mark, with notable victories in the UEFA Champions League, including memorable triumphs in 1968, 1999, and 2008. These successes on the continental stage have further enhanced Manchester United's reputation as a global football powerhouse.\n\nFurthermore, Manchester United has enjoyed success in other prestigious tournaments, such as the FA Charity Shield, the League Cup, and various minor trophies, adding to its extensive collection of silverware.\n\nOverall, Manchester United's trophy haul reflects its enduring excellence and competitiveness on the footballing stage, serving as a testament to the club's rich legacy and the passion of its players and fans alike.",  // trophiesBigInfo
                            red,  // kitColor
                            manchester  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.WestHamUnited,  // name
                            "WHUFC",  // initials
                            LocalDate.of(1895, 7, 5),  // created
                            45000000,  // budget
                            "West Ham United Football Club has a storied history woven with triumphs, challenges, and unwavering loyalty from its devoted fanbase. Established in 1895, the club has forged its identity through a commitment to attractive, attacking football and a dedication to nurturing local talent.",  // historySmallInfo
                            "Throughout its existence, West Ham United has experienced highs and lows, from memorable victories to hard-fought battles against relegation. However, the club's spirit remains unbroken, fueled by the passion and resilience of its players and supporters.\n\nWith iconic moments such as their famous FA Cup triumph in 1964 and their historic move to the London Stadium in 2016, West Ham United continues to captivate football enthusiasts worldwide. Despite the absence of major league titles, the Hammers' legacy is defined by their rich heritage, vibrant community, and the enduring bond between the club and its passionate fans.",  // historyBigInfo
                            "The fans of West Ham United, affectionately known as the 'Hammers,' are the lifeblood of the club, infusing every match with passion, energy, and unwavering support. Renowned for their unwavering dedication and fierce loyalty, West Ham supporters create an electrifying atmosphere at every game, transforming the London Stadium into a fortress of noise and fervor.",  // fansSmallInfo
                            "Through thick and thin, the Hammers faithful stand by their team, celebrating victories and rallying behind the players in times of adversity. Their chants echo through the stands, their colors adorn the stadium, and their unwavering support inspires players to give their all on the pitch.\n\nThe connection between West Ham United and its fans runs deep, transcending the boundaries of the football pitch. Whether at home or on the road, Hammers supporters travel far and wide to show their allegiance, creating a sea of claret and blue wherever they go. United by their love for the club and their unwavering belief in its potential, West Ham fans embody the spirit of East London - resilient, passionate, and proud.",  // fansBigInfo
                            "West Ham United, while not boasting a plethora of major trophies, has enjoyed success in various competitions throughout its history.",  // trophiesSmallInfo
                            "In addition to their FA Cup victory, West Ham has experienced success in the lower divisions of English football, clinching the Football League Second Division title on three occasions in 1958, 1981, and 1989. These accomplishments highlight the club's resilience and ability to compete at different levels of the English football pyramid.\n\nWhile West Ham's trophy cabinet may not be as extensive as some of their counterparts, the club continues to strive for success, driven by the passion and unwavering support of their dedicated fanbase. With each season, the Hammers aim to add to their trophy collection, further solidifying their place in the rich tapestry of English football history.",  // trophiesBigInfo
                            red,  // kitColor
                            london  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.BrightonHoveAlbion,  // name
                            "BHAFC",  // initials
                            LocalDate.of(1901, 8, 21),  // created
                            35000000,  // budget
                            "Founded in 1901, Brighton & Hove Albion Football Club has a rich and storied history that spans over a century. Situated in the vibrant coastal city of Brighton & Hove, the club has been a focal point of the community, captivating fans with its thrilling matches and enduring spirit.",  // historySmallInfo
                            "From its early days at the Goldstone Ground to its modern home at the American Express Community Stadium, Brighton & Hove Albion has undergone a remarkable journey of growth and transformation. The club has weathered the highs and lows of professional football, experiencing moments of triumph and adversity along the way.\n\nBrighton & Hove Albion's rise to prominence culminated in its return to the top-flight of English football in 2017, marking a historic achievement for the club and its loyal supporters. The Seagulls have since established themselves as a competitive force in the Premier League, showcasing their talent and determination on the national stage.\n\nThroughout its history, Brighton & Hove Albion has been defined by its passionate fanbase, known affectionately as the 'Seagulls'. These dedicated supporters have stood by their team through thick and thin, filling stadiums with chants, songs, and a sea of blue and white.\n\nAs Brighton & Hove Albion continues to build upon its legacy, the club remains a symbol of pride and unity for the city and its fans. With each passing season, the Seagulls soar to new heights, driven by a commitment to excellence and a shared love for the beautiful game.",  // historyBigInfo
                            "The fans of Brighton & Hove Albion Football Club, affectionately known as the 'Seagulls', are the lifeblood of the team, infusing every match with passion, energy, and unwavering support. From the stands of the American Express Community Stadium to away matches across the country, the Seagulls faithful create an electric atmosphere that fuels the players on the pitch.",  // fansSmallInfo
                            "Renowned for their loyalty and dedication, Brighton & Hove Albion fans stand by their team through every twist and turn of the season, celebrating victories and rallying behind the players in times of challenge. Their chants echo through the stadium, their colors adorn the stands, and their unwavering support inspires players to give their all on the field.\n\nThe bond between Brighton & Hove Albion and its fans runs deep, transcending the boundaries of the football pitch. Whether at home or on the road, Seagulls supporters travel far and wide to show their allegiance, creating a sea of blue and white wherever they go.\n\nBeing a Brighton & Hove Albion fan is more than just supporting a football club; it's about community, camaraderie, and shared experiences. The Seagulls faithful embody the spirit of Brighton & Hove - vibrant, resilient, and full of pride.",  // fansBigInfo
                            "Despite Brighton & Hove Albion Football Club never clinching major trophies in the top leagues or national cups, its journey through English football history is defined by resilience, commitment, and unwavering dedication to its supporters. Founded in 1901, the club has ingrained itself within its community.",  // trophiesSmallInfo
                            "Throughout its existence, Brighton & Hove Albion has remained steadfast in its pursuit of excellence on the pitch, continuously striving to represent its community with pride and passion. Despite not achieving major silverware, the club's legacy is marked by memorable moments, unwavering support from its fans, and a commitment to its core values.\n\nAlthough Brighton & Hove Albion claimed the championship in the Second Division (Championship) during the seasons 1957/1958 and 2001/2002, it has yet to secure major trophies in the top leagues or national cups. Nonetheless, the club's journey is characterized by resilience, unity, and an enduring bond with its loyal supporters.\n\nAs Brighton & Hove Albion continues its journey in English football, its legacy is not defined solely by trophies but by the spirit, resilience, and unwavering dedication that have shaped its identity throughout history.",  // trophiesBigInfo
                            blue,  // kitColor
                            brighton  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.Wolverhampton,  // name
                            "Wolves",  // initials
                            LocalDate.of(1877, 8, 1),  // created
                            50000000,  // budget
                            "Wolverhampton Wanderers Football Club, commonly known as Wolves, boasts a rich history deeply entrenched in English football's tapestry. Established in 1877, the club's journey has been marked by various peaks and valleys, shaping its identity as a resilient and iconic institution in the sport.",  // historySmallInfo
                            "During the 1950s, Wolves experienced a golden era under the stewardship of legendary manager Stan Cullis, clinching three Football League titles within six years. These triumphs not only solidified Wolves' status as a dominant force in English football but also etched their name in the annals of the game's history.\n\nBeyond domestic success, Wolves have left an indelible mark on the European stage, notably reaching the final of the European Cup in 1959-1960. Although they fell short against Real Madrid, their journey to the final stands as a testament to their ambition and prowess in continental competition.\n\nIn recent years, Wolves have experienced a renaissance, returning to the Premier League and establishing themselves as formidable competitors under the ownership of Fosun International and the guidance of manager Nuno Espírito Santo. As they navigate the modern landscape of football, Wolves continue to carry the hopes and dreams of their passionate fanbase, embodying the spirit of perseverance, unity, and pride that defines their storied legacy.",  // historyBigInfo
                            "The fans of Wolverhampton Wanderers, affectionately known as the 'Wolves faithful,' are the heartbeat of the club, infusing every match with passion, loyalty, and unwavering support. From the stands of Molineux Stadium to away games across the country, Wolves supporters create an electric atmosphere that fuels the players on the pitch.",  // fansSmallInfo
                            "Renowned for their unwavering dedication, Wolves fans stand by their team through thick and thin, celebrating victories and rallying behind the players in times of challenge. Their chants reverberate throughout the stadium, their colors adorn the stands, and their unwavering support inspires players to give their all on the field.\n\nThe bond between Wolverhampton Wanderers and its fans runs deep, transcending the boundaries of the football pitch. Whether at home or on the road, Wolves supporters travel far and wide to show their allegiance, creating a sea of gold and black wherever they go.\n\nBeing a Wolves fan is more than just supporting a football club; it's about community, camaraderie, and shared experiences. The Wolves faithful embody the spirit of Wolverhampton - resilient, passionate, and proud.",  // fansBigInfo
                            "Wolverhampton Wanderers Football Club boasts a distinguished trophy cabinet, adorned with various titles and honors that underscore its rich history and success in English football. While the club's trophy haul may not rival some of its counterparts, Wolves have enjoyed notable triumphs both domestically and internationally.",  // trophiesSmallInfo
                            "At the forefront of Wolverhampton Wanderers' trophy collection are its league championships. The club has clinched the English top-flight title on three occasions, with triumphs in the 1953-1954, 1957-1958, and 1958-1959 seasons, showcasing its dominance during that era.\n\nIn addition to league successes, Wolverhampton Wanderers has also excelled in domestic cup competitions. The club has won the FA Cup four times, with victories in 1893, 1908, 1949, and 1960, further solidifying its place in English football history.\n\nWhile Wolves' exploits in European competitions may not be as extensive, the club has made notable appearances and contributions on the continental stage. The highlight of Wolverhampton Wanderers' European campaign came in 1972 when they reached the final of the UEFA Cup, demonstrating their prowess beyond the shores of England.",  // trophiesBigInfo
                            yellow,  // kitColor
                            wolverhampton  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.NewcastleUnited,  // name
                            "NUFC",  // initials
                            LocalDate.of(1892, 12, 9),  // created
                            70000000,  // budget
                            "Newcastle United Football Club has a rich and storied history that dates back to its formation in 1892. Throughout the years, the club has experienced both triumphs and challenges, shaping its identity as one of the most iconic teams in English football.",  // historySmallInfo
                            "Founded as a response to the closing of the East End's Victoria Cricket Ground and the desire to establish a football team, Newcastle United quickly became a prominent force in the sport. The club's early years were marked by success, including winning the league title in 1904, 1905, and 1907 under the guidance of legendary manager Frank Watt.\n\nNewcastle United continued to enjoy success in the following decades, capturing numerous league titles and FA Cups. The club's iconic black and white stripes became synonymous with its rich history and passionate fanbase, who faithfully supported their team through thick and thin.\n\nDespite periods of struggle and relegation battles, Newcastle United's loyal supporters have remained steadfast in their support, filling St James' Park with their unwavering enthusiasm and creating an electrifying atmosphere on match days.\n\nIn recent years, Newcastle United has faced challenges both on and off the pitch, but the club's legacy remains firmly intact. With a passionate fanbase, a proud history, and a commitment to returning to the pinnacle of English football, Newcastle United continues to strive for success and carry on its tradition of excellence.",  // historyBigInfo
                            "The fans of Newcastle United Football Club are the heartbeat of the team, infusing every match with passion, loyalty, and unwavering support. Renowned for their fervent dedication and vocal presence, Newcastle fans create an electrifying atmosphere at St James' Park, turning every home game into an unforgettable spectacle.",  // fansSmallInfo
                            "Through the highs and lows of the club's journey, Newcastle supporters stand by their team with unwavering loyalty, filling the stadium with chants, songs, and a sea of black and white. Their fervor and dedication inspire players on the pitch and intimidate opponents, creating a formidable fortress for their beloved club.\n\nThe bond between Newcastle United and its fans runs deep, transcending generations and uniting supporters from all walks of life. Whether at home or away, Newcastle fans travel far and wide to support their team, demonstrating their unwavering commitment and passion for the club.\n\nFor Newcastle fans, supporting their team is more than just a hobby; it's a way of life. Their enduring loyalty and passionate support embody the spirit of Newcastle and serve as a driving force behind the club's pursuit of success on the pitch.",  // fansBigInfo
                            "Newcastle United Football Club may not boast as many trophies as some of their counterparts, but they have a rich history in English football. Established in 1892, the club has had its share of highs and lows, from winning league titles to facing relegation battles.",  // trophiesSmallInfo
                            "The loyal fans of Newcastle, known as the Toon Army, are the heartbeat of the club. Their unwavering support, chants, and passion create an electrifying atmosphere at St James' Park, inspiring players and intimidating opponents alike.\n\nWhile Newcastle's trophy cabinet may not overflow, the club has secured notable triumphs over the years. This includes several league titles, FA Cups, and other domestic honors, showcasing their enduring presence in English football.\n\nIn recent years, Newcastle has faced challenges, but their fans remain steadfast in their support. With a rich history behind them and passionate fans by their side, Newcastle United continues to strive for success and leave their mark on the footballing world.",  // trophiesBigInfo
                            black,  // kitColor
                            newCastle  // town
                    ),
                    new Team(
                            competition,
                            TeamNames.Ipswich,  // name
                            "ITFC",  // initials
                            LocalDate.of(1878, 10, 16),  // created
                            55000000,  // budget
                            "Ipswich FC, a historic English football club based in Ipswich, Suffolk, has made a triumphant return to the Premier League in 2024, marking a significant milestone in its storied history. The club's journey to the top flight has been characterized by resilience, determination, and a steadfast commitment to excellence.",  // historySmallInfo
                            "Founded in 1878, Ipswich FC has a rich heritage deeply ingrained in the fabric of English football. Throughout its existence, the club has experienced its fair share of highs and lows, from memorable triumphs to challenging setbacks. However, its unwavering spirit and passionate fan base have remained constants, fueling the team's aspirations for success.\n\nUnder the astute leadership of their manager and the dedication of a talented squad, Ipswich FC has navigated the competitive landscape of the lower divisions with aplomb, steadily climbing the ranks in pursuit of their ultimate goal. Their promotion to the Premier League signifies the culmination of years of hard work and dedication, as well as a testament to the club's ambition and vision for the future.\n\nAs Ipswich FC prepares to embark on their Premier League campaign, they do so with a sense of excitement and determination.",  // historyBigInfo
                            "Ipswich FC's passionate fan base has been a driving force behind the club's journey back to the Premier League. Through thick and thin, these loyal supporters have stood by their team, filling the stands with unwavering enthusiasm and unwavering loyalty.",  // fansSmallInfo
                            "For generations, the people of Ipswich have lived and breathed football, their devotion to the club transcending mere fandom to become a way of life. From the jubilant celebrations of victories to the unwavering support during challenging times, the bond between the team and its fans runs deep.\n\nNow, as Ipswich FC prepares for life in the Premier League once again, the anticipation among supporters is palpable. Their unwavering belief in the team's abilities and their unyielding support will serve as a source of inspiration as the club takes on the best teams in the country.\n\nWith every chant, every cheer, and every display of unwavering loyalty, Ipswich FC's fans will be there, standing shoulder to shoulder with their beloved team, ready to write the next chapter in the club's illustrious history.",  // fansBigInfo
                            "Ipswich Town Football Club, commonly known as Ipswich FC, boasts a rich and storied history within English football. Established in 1878, Ipswich FC has been a fixture in the English football landscape for well over a century. However, it wasn't until the late 1950s and early 1960s that the club truly began to make its mark on the national stage.",  // trophiesSmallInfo
                            "Under the management of the legendary Sir Alf Ramsey, Ipswich FC experienced its golden era. Ramsey guided the team to the Football League First Division title in the 1961-1962 season, marking the club's first top-flight championship. This triumph was followed by further success in the 1970s under manager Bobby Robson, who led Ipswich to victory in the FA Cup in 1978 and the UEFA Cup in 1981.\n\nThroughout the years, Ipswich FC has seen its fair share of ups and downs, experiencing periods of both glory and struggle. Despite facing relegation from the Premier League in the early 2000s, the club has maintained a passionate and dedicated fan base, known for their unwavering support through thick and thin.\n\nToday, Ipswich FC continues to compete in the Football League, striving to reclaim its former glory and solidify its place among the elite clubs in English football.",  // trophiesBigInfo
                            blue,  // kitColor
                            ipswich  // town
                    )
            ));
        }
        if(clubSocialMediaRepository.count() == 0){
            this.clubSocialMediaRepository.saveAll(List.of(
                    new ClubSocialMedia(teamRepository.findByName(TeamNames.Arsenal).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://www.arsenal.com/tickets?field_arsenal_team_target_id=1&revision_information=",
                            "https://www.arsenal.com/",
                            "https://twitter.com/arsenal",
                            "https://www.instagram.com/arsenal/",
                            "https://www.facebook.com/Arsenal/",
                            "https://www.youtube.com/@arsenal",
                            "https://www.tiktok.com/@arsenal"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.AstonVilla).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://tickets.avfc.co.uk/en-GB/categories/home-tickets",
                            "https://www.avfc.co.uk/",
                            "https://twitter.com/AVFCOfficial",
                            "https://www.instagram.com/avfcofficial/",
                            "https://www.facebook.com/avfcofficial/?locale",
                            "https://www.youtube.com/@avfcofficial",
                            "https://www.tiktok.com/@avfcofficial"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.BournemouthAFC).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://tickets.afcb.co.uk/content",
                            "https://www.afcb.co.uk",
                            "https://twitter.com/afcbournemouth",
                            "https://www.instagram.com/afcb/",
                            "https://www.facebook.com/afcb/",
                            "https://www.youtube.com/channel/UCeOCuVSSweaEj6oVtJZEKQw",
                            "https://www.tiktok.com/@afcb"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.Brentford).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://www.brentfordfc.com/en/ticket-information",
                            "https://www.brentfordfc.com/en",
                            "https://twitter.com/brentfordfc",
                            "https://www.instagram.com/brentfordfc/",
                            "https://www.facebook.com/brentfordfootballclub1889/",
                            "https://www.youtube.com/channel/UCAalMUm3LIf504ItA3rqfug",
                            "https://www.tiktok.com/@brentfordfc"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.BrightonHoveAlbion).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://tickets.brightonandhovealbion.com",
                            "https://www.brightonandhovealbion.com",
                            "https://twitter.com/OfficialBHAFC",
                            "https://www.instagram.com/officialbhafc/",
                            "https://www.facebook.com/officialbhafc/",
                            "https://www.youtube.com/channel/UCf-cpC9WAdOsas19JHipukA",
                            "https://www.tiktok.com/@officialbhafc"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.Chelsea).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://www.chelseafc.com/en/tickets/mens-tickets",
                            "https://www.chelseafc.com/en",
                            "https://twitter.com/ChelseaFC",
                            "https://www.instagram.com/chelseafc/",
                            "https://www.facebook.com/ChelseaFC/",
                            "https://www.youtube.com/channel/UCU2PacFf99vhb3hNiYDmxww",
                            "https://www.tiktok.com/@chelseafc"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.CrystalPalace).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://www.cpfc.co.uk/tickets/",
                            "https://www.cpfc.co.uk",
                            "https://twitter.com/cpfc",
                            "https://www.instagram.com/cpfc/",
                            "https://www.facebook.com/officialcpfc/",
                            "https://www.youtube.com/channel/UCWB9N0012fG6bGyj486Qxmg",
                            "https://www.tiktok.com/@cpfc"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.Everton).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://www.evertonfc.com/tickets-hospitality",
                            "https://www.evertonfc.com/home",
                            "https://twitter.com/everton",
                            "https://www.instagram.com/everton/",
                            "https://www.facebook.com/Everton/",
                            "https://www.youtube.com/EVERTON",
                            "https://www.tiktok.com/@everton"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.Fulham).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://www.fulhamfc.com/tickets-and-hospitality/match-tickets/",
                            "https://www.fulhamfc.com",
                            "https://twitter.com/fulhamfc",
                            "https://www.instagram.com/fulhamfc/",
                            "https://www.facebook.com/FulhamFC/",
                            "https://www.youtube.com/channel/UC2VLfz92cTT8jHIFOecC-LA",
                            "https://www.tiktok.com/@fulhamfc"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.Ipswich).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://tickets.avfc.co.uk/en-GB/categories/home-tickets",
                            "https://www.itfc.co.uk",
                            "https://twitter.com/ipswichtown",
                            "https://www.instagram.com/ipswichtown/",
                            "https://www.facebook.com/officialitfc/",
                            "https://www.youtube.com/user/officialitfc",
                            "https://www.tiktok.com/@ipswichtown"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.LeicesterCity).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://tickets.lcfc.com/en-gb/categories/home%20tickets",
                            "https://www.lcfc.com/?lang=EN",
                            "https://twitter.com/LCFC",
                            "https://www.instagram.com/lcfc/",
                            "https://www.facebook.com/lcfc/",
                            "https://www.youtube.com/@LCFC",
                            "https://www.tiktok.com/@lcfc"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.Liverpool).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://www.liverpoolfc.com/tickets/tickets-availability",
                            "https://www.liverpoolfc.com/",
                            "https://twitter.com/lfc",
                            "https://www.instagram.com/liverpoolfc/",
                            "https://www.facebook.com/LiverpoolFC/",
                            "https://www.youtube.com/channel/UC9LQwHZoucFT94I2h6JOcjw",
                            "https://www.tiktok.com/@liverpoolfc"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.ManchesterCity).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://www.mancity.com/tickets/mens",
                            "https://www.mancity.com",
                            "https://twitter.com/mancity",
                            "https://www.instagram.com/mancity/",
                            "https://www.facebook.com/mancity/",
                            "https://www.youtube.com/@mancity",
                            "https://www.tiktok.com/@mancity"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.ManchesterUnited).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://tickets.manutd.com/en-GB/categories/home-tickets",
                            "https://www.manutd.com",
                            "https://twitter.com/ManUtd",
                            "https://www.instagram.com/manchesterunited/",
                            "https://www.facebook.com/manchesterunited/",
                            "https://www.youtube.com/manutd",
                            "https://www.tiktok.com/@manutd"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.NewcastleUnited).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://www.newcastleunited.com/tickets/on-sale-dates/",
                            "https://www.newcastleunited.com",
                            "https://twitter.com/NUFC",
                            "https://www.instagram.com/nufc/",
                            "https://www.facebook.com/newcastleunited",
                            "https://www.youtube.com/@NUFC",
                            "https://www.tiktok.com/@nufc"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.NottinghamForest).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://www.nottinghamforest.co.uk/category/tickets",
                            "https://www.nottinghamforest.co.uk",
                            "https://twitter.com/NFFC",
                            "https://www.instagram.com/officialnffc/",
                            "https://www.facebook.com/officialnffc/",
                            "https://www.youtube.com/channel/UCyAxjuAr8f_BFDGCO3Htbxw",
                            "https://www.tiktok.com/@officialnffc"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.Southampton).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://www.southamptonfc.com/en/matches/mens-team",
                            "https://www.southamptonfc.com/en",
                            "https://x.com/southamptonfc",
                            "https://www.instagram.com/southamptonfc/",
                            "https://www.facebook.com/southamptonfc/",
                            "https://www.youtube.com/channel/UCxvXjfiIHQ2O6saVx_ZFqnw",
                            "https://www.tiktok.com/@southamptonfc"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.TottenhamHotspur).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://www.tottenhamhotspur.com/tickets/buy-tickets/home-tickets/",
                            "https://www.tottenhamhotspur.com/",
                            "https://twitter.com/SpursOfficial",
                            "https://www.instagram.com/spursofficial/",
                            "https://www.facebook.com/TottenhamHotspur/",
                            "https://www.youtube.com/spursofficial",
                            "https://www.tiktok.com/@spursofficial"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.WestHamUnited).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://www.whufc.com/tickets/home-matches",
                            "https://www.whufc.com",
                            "https://twitter.com/westham",
                            "https://www.instagram.com/westham/",
                            "https://www.facebook.com/WestHam/",
                            "https://www.youtube.com/westhamunited",
                            "https://www.tiktok.com/@westham"
                    ),

                    new ClubSocialMedia(teamRepository.findByName(TeamNames.Wolverhampton).orElseThrow(() -> new ResourceNotFoundException("ERROR IN DATABASE! THE TEAM DOESN'T EXIST!")),
                            "https://www.wolves.co.uk/tickets/",
                            "https://www.wolves.co.uk",
                            "https://twitter.com/Wolves",
                            "https://www.instagram.com/wolves/",
                            "https://www.facebook.com/wolves/",
                            "https://www.youtube.com/channel/UCQ7Lqg5Czh5djGK6iOG53KQ",
                            "https://www.tiktok.com/@wolves"
                    )
            ));
        }
    }

}
