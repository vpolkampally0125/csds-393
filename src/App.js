import Button from "react-bootstrap/Button"
import Stack from "react-bootstrap/Stack"
import container from "react-bootstrap/Container"

function App() {
  return (
  <container className = 'my-2'>
    <Stack direction = "horizontal" gap = "2" className = "mb-4">
      <h1 className = "me-auto">Budget Summary</h1>
      <Button variant = "outline-primary">+ Expense</Button>
      <Button variant = "outline-primary">+ Income</Button>
      <Button variant = "outline-primary">+ Debt</Button>
      <Button variant = "outline-primary">Add Category</Button>
    </Stack>
    

  </container>
  )
}

export default App;
