package entity;
import java.util.ArrayList;
import java.util.List;

public class Food {
    private String name;           // Имя еды
    private int id;                // Уникальный идентификатор
    private List<String> recipes;  // Список ссылок на рецепты


    public Food(String name, int id, List<String> recipes) {
        this.name = name;
        this.id = id;
        this.recipes = new ArrayList<>(recipes);
    }


    public Food(String name, int id) {
        this.name = name;
        this.id = id;
        this.recipes = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getRecipes() {
        return recipes;
    }


    public void addRecipe(String recipeLink) {
        recipes.add(recipeLink);
    }


    public boolean removeRecipe(String recipeLink) {
        return recipes.remove(recipeLink);
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", recipes=" + recipes +
                '}';
    }
}