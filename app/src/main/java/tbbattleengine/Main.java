package tbbattleengine;

public class App {
    private Mode currentMode;

    public App() {
        currentMode = Mode.PLAYER;   

        // Engine Selector
        switch(currentMode) {
            case Mode.PLAYER:
                getGreeting();
                break;
            case Mode.WEAPON:
                break;
            case Mode.BOTH:
                break;
        }
    }

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}