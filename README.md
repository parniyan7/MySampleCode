
# MySampleCode

MySampleCode is a clean architecture project that uses the MVVM pattern. It consists of two modules: "app" and "core". The "app" module is responsible for context-based interactions, especially user-related interactions, while the "core" module provides data. These two modules interact via use cases.

## Architecture Overview

The MVVM architecture is used to ensure separation of concerns and make the codebase easier to maintain. The app module contains the view, which is responsible for rendering the user interface. The view communicates with the viewModel, which contains the business logic of the app. The viewModel interacts with the useCase, which is responsible for retrieving data from the repository in the core module.

## Delegate Adapter

A Delegate Adapter has been implemented in this project. This is considered a best practice for using adapters as it allows the adapter to delegate the rendering of each list item to a separate component. This makes the adapter more modular and easier to maintain.

## ResponseState

A ResponseState has been implemented to simulate how a real API call would look like. Although the data used in this project is mocked and localized, the ResponseState is a useful tool for testing and debugging.

## Testing

Several tests have been added to the project to ensure that the code is functional and maintainable. These include ViewModel tests, UseCase tests, and Repository tests. The use of tests helps catch errors early on and ensures that the code is functioning as expected.

## Design Patterns

The Singleton design pattern has been used in this project where necessary. This design pattern ensures that there is only one instance of a class created and that it is easily accessible throughout the entire project.

## Installation

To install this project, simply clone the repository and open it in your IDE of choice. Ensure that you have all the necessary dependencies installed before running the project.

## Usage

To use this project, simply run it in your IDE or emulator. The app module contains the main activity, which serves as the entry point of the application.

## Contributing

If you would like to contribute to this project, please feel free to submit a pull request or open an issue. Contributions are always welcome!

## Credits

This project was created by [Parniyan Mousaie]. 


## Contact

If you have any questions or issues with this project, please feel free to contact [mousaieparniyan@gmail.com].