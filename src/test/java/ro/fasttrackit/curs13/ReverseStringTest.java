package ro.fasttrackit.curs13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReverseStringTest {
    ReverseString reverseString;

    @BeforeEach
    void setup() {
        reverseString = new ReverseString();
    }

    @Test
    @DisplayName("reverse(null) = null")
    void testNullReturnsNull() {
        String result = reverseString.reverse(null);

        assertThat(result).isNull();
    }

    @Test
    @DisplayName("reverse('') = ''")
    void testEmptyString() {
        String result = reverseString.reverse("");

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("reverse('a') = 'a' ")
    void testSingleLetter() {
        String result = reverseString.reverse("a");

        assertThat(result).isEqualTo("a");
    }

    @Test
    @DisplayName("reverse('test') = 'tset'")
    void testWord() {
        String result = reverseString.reverse("test");

        assertThat(result).isEqualTo("tset");
    }

    @Test
    @DisplayName("test long word")
    void testLargeWord() {
        String result = reverseString.reverse("jfkdls;ajfkl;asdjfkl;asdjkflasdjklfjasd;l");

        assertThat(result).isEqualTo("l;dsajflkjdsalfkjdsa;lkfjdsa;lkfja;sldkfj");
    }


    @Test
    @DisplayName("WHEN the string is too long THEN recursivity fails!")
    void breakRecursivity() {
        String tooLong = "t".repeat(100_000);

        StackOverflowError thrownError = assertThrows(StackOverflowError.class,
                () -> reverseString.reverse(tooLong));

        assertThat(thrownError.getMessage()).isEqualTo(null);
    }
}
