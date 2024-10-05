import "./style/globals.css";
import { ModeToggle } from "./components/mode-toggle";
import { ThemeProvider } from "./components/theme-provider";
import { Button } from "./components/ui/button";
import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
} from "./components/ui/card";
import { ShoppingCart } from "lucide-react";
import {
    Table,
    TableBody,
    TableCaption,
    TableCell,
    TableFooter,
    TableHead,
    TableHeader,
    TableRow,
} from "./components/ui/table";

function App() {
    const invoices = [
        {
            invoice: "INV001",
            paymentStatus: "Paid",
            totalAmount: "$250.00",
            paymentMethod: "Credit Card",
        },
        {
            invoice: "INV002",
            paymentStatus: "Pending",
            totalAmount: "$150.00",
            paymentMethod: "PayPal",
        },
        {
            invoice: "INV003",
            paymentStatus: "Unpaid",
            totalAmount: "$350.00",
            paymentMethod: "Bank Transfer",
        },
        {
            invoice: "INV004",
            paymentStatus: "Paid",
            totalAmount: "$450.00",
            paymentMethod: "Credit Card",
        },
        {
            invoice: "INV005",
            paymentStatus: "Paid",
            totalAmount: "$550.00",
            paymentMethod: "PayPal",
        },
        {
            invoice: "INV006",
            paymentStatus: "Pending",
            totalAmount: "$200.00",
            paymentMethod: "Bank Transfer",
        },
        {
            invoice: "INV007",
            paymentStatus: "Unpaid",
            totalAmount: "$300.00",
            paymentMethod: "Credit Card",
        },
    ];

    return (
        <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
            <div className="flex mx-auto my-4 h-20 p-4 items-center justify-between max-w-5xl bg-green-900 rounded-lg">
                <div className="flex gap-4 p-2">
                    <ShoppingCart color="#16A249" />
                    <p className="font-bold text-white">RANDOM CHECKOUT</p>
                </div>
                <ModeToggle />
            </div>
            <div className="container mx-auto justify-center max-w-5xl">
                <Card>
                    <CardHeader>
                        <CardTitle className="font-bold text-primary text-xl">
                            PRODUTO ALEATÓRIO
                        </CardTitle>
                        <CardDescription>
                            Descrição do produto aleatório
                        </CardDescription>
                        <CardDescription>R$ 100,00</CardDescription>
                    </CardHeader>
                    <CardContent>
                        <CardTitle className="py-4 uppercase font-bold text-secondary-foreground">
                            Seus dados
                        </CardTitle>
                        <div className="flex gap-8 py-1 px-4">
                            <p>Nome:</p>
                            <p>Antônio de Jesus</p>
                        </div>
                        <div className="flex gap-8 py-1 px-4">
                            <p>E-mail:</p>
                            <p>antonio@gmail.com</p>
                        </div>
                    </CardContent>
                    <CardContent>
                        <CardTitle className="py-4 uppercase font-bold text-secondary-foreground">
                            Pagamento
                        </CardTitle>
                        <div className="flex gap-8 py-1 px-4 justify-between w-[360px] text-nowrap">
                            <p>Nome no Cartão:</p>
                            <p>Antônio de Jesus</p>
                        </div>
                        <div className="flex gap-8 py-1 px-4 justify-between w-[360px] text-nowrap">
                            <p>Número do Cartão:</p>
                            <p>5577 6657 0908 9835</p>
                        </div>
                        <div className="flex gap-8 py-1 px-4 justify-between w-[360px] text-nowrap">
                            <p>Data de Validade:</p>
                            <p>02/04/2026</p>
                        </div>
                        <div className="flex gap-8 py-1 px-4 justify-between w-[360px] text-nowrap">
                            <p>CVV:</p>
                            <p>444</p>
                        </div>
                    </CardContent>
                    <CardFooter className="gap-4">
                        <Button>Enviar compra</Button>
                        <Button variant={"ghost"} className="border">
                            Aleatorizar
                        </Button>
                    </CardFooter>
                </Card>
                <div className="container mx-auto justify-center max-w-5xl my-4">
                    <Card>
                        <CardHeader>
                            <CardTitle className="font-bold text-primary text-xl">
                                TODOS OS PEDIDOS
                            </CardTitle>
                        </CardHeader>
                        <CardContent>
                            <Table>
                                <TableCaption>
                                    A list of your recent invoices.
                                </TableCaption>
                                <TableHeader>
                                    <TableRow>
                                        <TableHead className="w-[100px]">
                                            Invoice
                                        </TableHead>
                                        <TableHead>Status</TableHead>
                                        <TableHead>Method</TableHead>
                                        <TableHead className="text-right">
                                            Amount
                                        </TableHead>
                                    </TableRow>
                                </TableHeader>
                                <TableBody>
                                    {invoices.map((invoice) => (
                                        <TableRow key={invoice.invoice}>
                                            <TableCell className="font-medium">
                                                {invoice.invoice}
                                            </TableCell>
                                            <TableCell>
                                                {invoice.paymentStatus}
                                            </TableCell>
                                            <TableCell>
                                                {invoice.paymentMethod}
                                            </TableCell>
                                            <TableCell className="text-right">
                                                {invoice.totalAmount}
                                            </TableCell>
                                        </TableRow>
                                    ))}
                                </TableBody>
                                <TableFooter>
                                    <TableRow>
                                        <TableCell colSpan={3}>Total</TableCell>
                                        <TableCell className="text-right">
                                            $2,500.00
                                        </TableCell>
                                    </TableRow>
                                </TableFooter>
                            </Table>
                        </CardContent>
                    </Card>
                </div>
            </div>
        </ThemeProvider>
    );
}

export default App;
