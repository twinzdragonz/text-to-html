function parse_template($template) {
  // Split the template into a list of lines
  $lines = explode("\n", $template);

  // Initialize a stack to store the HTML tags that have been opened but not yet closed
  $tag_stack = [];

  // Initialize an empty string to store the output HTML string
  $html = '';

  // Iterate through each line of the template
  foreach ($lines as $line) {
    // Determine the number of spaces at the beginning of the line
    $indentation = strspn($line, ' ');

    // Split the line into a list of words
    $words = explode(' ', $line);

    // If the first word is a closing tag, pop the top element from the stack and append the corresponding closing tag to the output HTML string
    if ($words[0][0] === '/') {
      $tag = array_pop($tag_stack);
      $html .= str_repeat(' ', $indentation) . "</$tag>\n";
    // If the first word is an opening tag, append the opening tag to the output HTML string and push it onto the stack
    } elseif ($words[0][0] !== '/') {
      $tag = $words[0];
      $html .= str_repeat(' ', $indentation) . "<$tag>\n";
      array_push($tag_stack, $tag);
    // If the first word is not a tag, append the line to the output HTML string with the appropriate indentation
    } else {
      $html .= str_repeat(' ', $indentation) . "$line\n";
    }
  }

  // If there are any remaining elements in the stack, pop them off and append the corresponding closing tags to the output HTML string
  $tag_stack = array_reverse($tag_stack);
  foreach ($tag_stack as $tag) {
    $html .= "</$tag>\n";
  }

  return $html;
}

// Example usage
$template = 'table\n  thead\n    tr\n      td Heading 1\n      td Heading 2\n  tbody\n    tr\n      td Body 1\n      td Body 2';
$html = parse_template($template);
echo $html;