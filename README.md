# Currency Converter Bot

Currency Converter Bot is a Java-based Telegram bot that provides users with real-time currency exchange rates based on their individual preferences. The bot is built using the Spring Framework and supports three banks: PrivatBank, Monobank, and National Bank of Ukraine.

## Features

Currency Converter Bot allows users to:

- Choose their preferred bank for currency exchange rates
- Set the currency and number of decimal places they want to view
- Receive regular updates on currency exchange rates based on their unique settings

## Installation

To install Currency Converter Bot, follow these steps:

1. Clone the repository from GitHub: `git clone https://github.com/Sidroded/currency-converter-bot-go-it`
2. Install Gradle build system on your machine: https://gradle.org/install/
3. Open the project in your preferred IDE
4. Replace the Telegram bot token in the `application.properties` file with your own token
5. Run the application using `gradle bootRun` or build the jar using `gradle build` and run the jar file
6. Start chatting with the bot on Telegram

## Usage

Once the bot is installed, users can interact with it via the Telegram app. To get started, follow these steps:

1. Start a conversation with the bot on Telegram by searching for its username or by clicking on the link provided by the developer
2. Send the bot the command `/start` to initialize it
3. Use the following commands to interact with the bot:
   - `Отримати інфо`: Get the latest exchange rates based on your settings
   - `Налаштування`: Change your preferred settings bank, currency, and number of decimal places
   - `Банк`: Change your preferred bank
   - `Валюта`: Change your preferred currency
   - `Кількість знаків після коми`: Change your preferred number of decimal places
   - `Час сповіщень`: Change your preferred subscribe time
   - `Увімкнути повідомлення`: Subscribe to regular updates on exchange rates based on your settings
   - `Вимкнути повідомлення`: Unsubscribe from updates

## Contributing

Contributions to Currency Converter Bot are welcome! To contribute:

1. Fork the repository
2. Create a new branch for your changes
3. Make your changes and commit them to the new branch
4. Push your changes to your fork
5. Submit a pull request to the main repository

## Credits

Currency Converter Bot was created by Sidroded and is maintained by Sidroded and SofiiaZasymenko, ircecam, Andriver, SemenovVS.
