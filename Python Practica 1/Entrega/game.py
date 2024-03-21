import random
# Lista de palabras posibles
words = ["python", "programación", "computadora", "código", "desarrollo","inteligencia"]
# Elegir una palabra al azar
secret_word = random.choice(words)
# Número máximo de intentos permitidos
max_fails = 10
#numero de fallos del usuario
fails = 0
# Lista para almacenar las letras adivinadas
guessed_letters = []

print("¡Bienvenido al juego de adivinanzas!")
print("""Nivel de dificultad:
         1. Fácil
         2. medio
         3. Difícil
         ----------------------------------""")

#verifico que el usuario ingrese un número valido 
while True:
    level_dif = input('seleccione un nivel de dificultad (1,2 o 3): ')
    if level_dif >= '1' and level_dif <= '3':
        break
    print ('No has ingresado un valor correcto, intenta de nuevo!')

print("Estoy pensando en una palabra. ¿Puedes adivinar cuál es?")

match int(level_dif):
    #facil
    case 1:
        vocals = []
        for letter in secret_word:
            if letter in "aeiou":
                vocals.append(letter)
                guessed_letters.append(letter)
            else:
                vocals.append("_")
        word_displayed = "".join(vocals)
        
    #medio
    case 2:
        word_displayed = secret_word[0] + "_" * (len(secret_word) - 2) + secret_word[-1]
        guessed_letters.append(secret_word[0])
        guessed_letters.append(secret_word[-1])
        
    #dificil
    case 3:
        word_displayed = "_" * len(secret_word)
        

# Mostrarla palabra parcialmente adivinada
print(f"Palabra: {word_displayed}")
while fails < max_fails:
  # Pedir al jugador que ingrese una letra
  letter = input("Ingresa una letra: ").lower()
  # Verificar si la letra ya ha sido adivinada
  if letter in guessed_letters:
     print("Ya has intentado con esa letra. Intenta con otra.")
     if letter not in secret_word:
         fails += 1
     continue
  # Agregar la letra a la lista de letras adivinadas
  guessed_letters.append(letter)
  # Verificar si la letra está en la palabra secreta
  if letter in secret_word and letter != "": 
     print("¡Bien hecho! La letra está en la palabra.")
  else:
      print("Lo siento, la letra no está en la palabra.")
      fails += 1
  # Mostrar la palabra parcialmente adivinada
  letters = []
  for letter in secret_word:
      if letter in guessed_letters:
          letters.append(letter)
      else:
          letters.append("_")
  word_displayed = "".join(letters)
  print(f"Palabra: {word_displayed}")
  # Verificar si se ha adivinado la palabra completa
  if word_displayed == secret_word:
       print(f"¡Felicidades! Has adivinado la palabra secreta:{secret_word}")  
       break
else:
  print(f"¡Oh no! Has agotado tus {max_fails} intentos.")
  print(f"La palabra secreta era: {secret_word}")    