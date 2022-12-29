import java.util.ArrayDeque;
import java.util.Deque;

public class TemplateParser {
  public static String parseTemplate(String template) {
    // Split the template into a list of lines
    String[] lines = template.split("\n");

    // Initialize a stack to store the HTML tags that have been opened but not yet closed
    Deque<String> tagStack = new ArrayDeque<>();

    // Initialize an empty string to store the output HTML string
    StringBuilder html = new StringBuilder();

    // Iterate through each line of the template
    for (String line : lines) {
      // Determine the number of spaces at the beginning of the line
      int indentation = line.indexOf(line.trim());

      // Split the line into a list of words
      String[] words = line.split(" ");

      // If the first word is a closing tag, pop the top element from the stack and append the corresponding closing tag to the output HTML string
      if (words[0].charAt(0) == '/') {
        String tag = tagStack.pop();
        html.append(String.join("", Collections.nCopies(indentation, " "))).append("</").append(tag).append(">\n");
      // If the first word is an opening tag, append the opening tag to the output HTML string and push it onto the stack
      } else if (words[0].charAt(0) != '/') {
        String tag = words[0];
        html.append(String.join("", Collections.nCopies(indentation, " "))).append("<").append(tag).append(">\n");
        tagStack.push(tag);
      // If the first word is not a tag, append the line to the output HTML string with the appropriate indentation
      } else {
        html.append(String.join("", Collections.nCopies(indentation, " "))).append(line).append("\n");
      }
    }

    // If there are any remaining elements in the stack, pop them off and append the corresponding closing tags to the output HTML string
    while (!tagStack.isEmpty()) {
      String tag = tagStack.pop();
      html.append("</").append(tag).append(">\n");
    }

    return html.toString();
  }

  public static void main(String[] args) {
    // Example usage
    String template = "table\n  thead\n    tr\n      td Heading 1\n      td Heading 2\n  tbody\n    tr\n      td Body 1\n      td Body 2";
    String html = parseTemplate(template);
    System.out.println(html);
  }
}