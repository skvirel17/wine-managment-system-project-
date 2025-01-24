package control;

import entity.Wine;
import enums.WineTypeE;
import java.util.List;
import java.util.stream.Collectors;

public class WineFilterLogic {

    /**
     * Фильтрует список вин по заданным параметрам.
     *
     * @param wines         Список всех вин.
     * @param wineType      Тип вина (может быть null, если не фильтруется).
     * @param keywords      Ключевые слова для фильтрации описания (может быть пустым).
     * @return Отфильтрованный список вин.
     */
    public List<Wine> filterWines(List<Wine> wines, WineTypeE wineType, List<String> keywords) {
        return wines.stream()
                // Фильтр по типу вина
                .filter(wine -> wineType == null || wine.getWineType() == wineType)
                // Фильтр по ключевым словам
                .filter(wine -> keywords.isEmpty() || keywords.stream().anyMatch(keyword ->
                        wine.getDescription().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());
    }
}

