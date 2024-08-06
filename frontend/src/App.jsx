import { useState } from "react";
import "./App.css";
import "./assets/styles/custom.scss";

import Rotas from "./Rotas";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <Rotas />
    </>
  );
}

export default App;
