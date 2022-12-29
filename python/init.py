# untested
def parse_template(template):
    # Split the template into a list of lines
    lines = template.split('\n')

    # Initialize a stack to store the HTML tags that have been opened but not yet closed
    tag_stack = []

    # Initialize an empty string to store the output HTML string
    html = ''

    # Iterate through each line of the template
    for line in lines:
        # Determine the number of spaces at the beginning of the line
        indentation = len(line) - len(line.lstrip())

        # Split the line into a list of words
        words = line.split(' ')
        # If the first word is a closing tag, pop the top element from the stack and append the corresponding closing tag to the output HTML string
        if words[0] == '/':
            tag = tag_stack.pop()
            html += ' ' * indentation + f'</{tag}>\n'
        # If the first word is an opening tag, append the opening tag to the output HTML string and push it onto the stack
        elif words[0] != '/':
            tag = words[0]
            html += ' ' * indentation + f'<{tag}>\n'
            tag_stack.append(tag)
        # If the first word is not a tag, append the line to the output HTML string with the appropriate indentation
        else:
            html += ' '

    # If there are any remaining elements in the stack, pop them off and append the corresponding closing tags to the output HTML string
    while tag_stack != None :
        tag = tag_stack.pop
        print(tag)
        html += "</#{tag}>\n"
        print(html)
        


    return html


template = "table\n  thead\n    tr\n      td Heading 1\n      td Heading 2\n  tbody\n    tr\n      td Body 1\n      td Body 2"
html = parse_template(template)
print(html)
     