function parseTemplate(template) {
    // Split the template into a list of lines
    const lines = template.split('\n');
  
    // Initialize a stack to store the HTML tags that have been opened but not yet closed
    const tagStack = [];
  
    // Initialize an empty string to store the output HTML string
    let html = '';
  
    // Iterate through each line of the template
    lines.forEach((line) => {
      // Determine the number of spaces at the beginning of the line
      const indentation = line.search(/\S/);
  
      // Split the line into a list of words
      const words = line.split(' ');
  
      // If the first word is a closing tag, pop the top element from the stack and append the corresponding closing tag to the output HTML string
      if (words[0][0] === '/') {
        const tag = tagStack.pop();
        html += `${" ".repeat(indentation)}</${tag}>\n`;
      // If the first word is an opening tag, append the opening tag to the output HTML string and push it onto the stack
      } else if (words[0][0] !== '/') {
        const tag = words[0];
        html += `${" ".repeat(indentation)}<${tag}>\n`;
        tagStack.push(tag);
      // If the first word is not a tag, append the line to the output HTML string with the appropriate indentation
      } else {
        html += `${" ".repeat(indentation)}${line}\n`;
      }
    });
  
    // If there are any remaining elements in the stack, pop them off and append the corresponding closing tags to the output HTML string
    tagStack.reverse().forEach((tag) => {
      html += `</${tag}>\n`;
    });
  
    return html;
  }
  
  // Example usage
  const template = 'table\n  thead\n    tr\n      td Heading 1\n      td Heading 2\n  tbody\n    tr\n      td Body 1\n      td Body 2';
  const html = parseTemplate(template);
  console.log(html);