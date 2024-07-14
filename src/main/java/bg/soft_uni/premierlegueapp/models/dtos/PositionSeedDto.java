package bg.soft_uni.premierlegueapp.models.dtos;

public class PositionSeedDto {
    private int position;
    private int points;
    private String name;

    public PositionSeedDto(int position, int points, String name) {
        this.position = position;
        this.points = points;
        this.name = name;
    }

    public PositionSeedDto() {
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
