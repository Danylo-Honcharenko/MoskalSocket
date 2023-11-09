import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class WordTest {
    @Test
    public void forbiddenWordTrue() {
        List<Character> RU_letters = List.of('ё', 'ъ', 'ы', 'э');
        String word = "Рыба";
        boolean actual = new Word(word).forbidden(RU_letters);
        Assert.assertTrue(actual);
    }

    @Test
    public void forbiddenWordFalse() {
        List<Character> RU_letters = List.of('ё', 'ъ', 'ы', 'э');
        String word = "Привіт";
        boolean actual = new Word(word).forbidden(RU_letters);
        Assert.assertFalse(actual);
    }
}
