import re
# The strategy here is to use a fake number class to overload
# the arithmetic operators, allowing eval to do the token parsing.
# (https://www.reddit.com/r/adventofcode/comments/kfeldk/2020_day_18_solutions/)

class I(int):
# keep multiplication (*) the same, but change the 
# addition symbol depending on part one or part two
  def __mul__(self,other):
    return I(self.real * other.real)
  
  # modulo (%) is at the same level as multiplication
  def __mod__(self,other):
    return I(self.real + other.real)
  
  # power (**) is at a higher level than multiplication
  def __pow__(self,other):
    return I(self.real + other.real)

# replace every digit in the line with I(x) so that eval uses our fake int class
input = [re.sub(r'(\d+)', r'I(\1)', line.strip()) for line in open("input/day18.txt")]

# in part 1, multiplcation and addition are equal precedence
print("Part 1:", sum([eval(eq.replace('+','%')) for eq in input]))
# for part 2, addition has a higher precedence than multiplication
print("Part 2:", sum([eval(eq.replace('+','**')) for eq in input]))



